package com.example.demo.service.order;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class MyService {
    @Async
    public CompletableFuture<String> doSomethingAsync() {

        OrderManager orderManager = new OrderManager();

        var o1 = new Buy(100, UUID.randomUUID());
        orderManager.accept(o1);
        System.out.println("---------add"+o1);
        System.out.println("----pre----:"+orderManager.getAll());
        System.out.println("----evict----:"+orderManager.getFullyFulfilled());
        System.out.println("----post----:"+orderManager.getAll());

        var o2 = new Sell(150, UUID.randomUUID());
        orderManager.accept(o2);
        System.out.println("--------- add"+ o2);
        System.out.println("----pre----:"+orderManager.getAll());
        System.out.println("----evict----:"+orderManager.getFullyFulfilled());
        System.out.println("----post----:"+orderManager.getAll());

        var o2a = new Sell(150, UUID.randomUUID());
        orderManager.accept(o2a);
        System.out.println("--------- add"+ o2a);
        System.out.println("----pre----:"+orderManager.getAll());
        System.out.println("----evict----:"+orderManager.getFullyFulfilled());
        System.out.println("----post----:"+orderManager.getAll());

        var o3 = new Buy(200, UUID.randomUUID());
        orderManager.accept(o3);
        System.out.println("---------add"+o3);
        System.out.println("----pre----:"+orderManager.getAll());
        System.out.println("----evict----:"+orderManager.getFullyFulfilled());
        System.out.println("----post----:"+orderManager.getAll());




        return CompletableFuture.completedFuture("Result");
    }
}
