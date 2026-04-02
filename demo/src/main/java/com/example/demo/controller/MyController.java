package com.example.demo.controller;

import com.example.demo.service.MyService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
public class MyController {

    private final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @Async
    @GetMapping("/combine-data")
    public CompletableFuture<String> fetchCombinedData() {

        CompletableFuture<String> future = myService.doSomethingAsync().thenApply(data -> "Combined: " + data);
        return future;
    }


}
