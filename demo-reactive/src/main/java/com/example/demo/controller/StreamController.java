package com.example.demo.controller;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DefaultDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;

@RestController
public class StreamController {

    @GetMapping(path = "/stream/out-ndjson", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<String> streamNdJson() {
        return Flux.interval(Duration.ofSeconds(1)).take(5).map(i -> Instant.now().toString());
    }

    @GetMapping(path = "/stream/out-csv", produces = "text/csv")
    public Mono<Void> streamCSV(ServerHttpRequest request, ServerHttpResponse response) {
        response.getHeaders().setContentType(MediaType.valueOf("text/csv"));
        response.getHeaders().add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=export.csv");
        Flux<DataBuffer> csvFlux = generateCsvFlux();
        return response.writeWith(csvFlux);
    }

    private Flux<DataBuffer> generateCsvFlux() {
        Flux<DataBuffer> dataStream = Flux.interval(Duration.ofSeconds(1))
                .take(5)
                .map(i -> Instant.now())
                .map(this::instantToCsv)
                .map(this::stringToDataBuffer);
        return dataStream;
    }

    private String instantToCsv(Instant instant) {
        return instant.toString();
    }

    private DataBuffer stringToDataBuffer(String content) {
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = new DefaultDataBufferFactory().allocateBuffer(bytes.length);
        buffer.write(bytes);
        return buffer;
    }
}
