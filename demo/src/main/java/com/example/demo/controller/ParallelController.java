package com.example.demo.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping(value = "/api/parallel")
public class ParallelController {

    public static Date addSeconds(Date date, int seconds) {
        Calendar cal = Calendar.getInstance();
        cal.setTime((Date)date.clone());
        cal.add(Calendar.SECOND, seconds);
        return cal.getTime();
    }
    Date dt = Date.from(Instant.now());
    List<Date> dates = IntStream.rangeClosed(1, 1000).mapToObj(i -> addSeconds(dt,i)).collect(Collectors.toCollection(ArrayList::new));

    @Async
    @GetMapping(value = "/thread1")
    public void thread1() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dates.stream().map(d -> sdf.format(d)).parallel().forEach(System.out::println);
    }

}
