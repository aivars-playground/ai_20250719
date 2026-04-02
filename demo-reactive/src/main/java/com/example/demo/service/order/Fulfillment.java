package com.example.demo.service.order;

import java.util.HashMap;
import java.util.Map;

public class Fulfillment<Order> {
    public int amount() {
        return map.values().stream().reduce(0, Integer::sum);
    }

    private Map<Order, Integer> map = new HashMap<>();

    public void add(Order s, int amount) {
        map.put(s, amount);
    }
}
