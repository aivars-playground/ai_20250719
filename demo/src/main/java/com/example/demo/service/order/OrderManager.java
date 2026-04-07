package com.example.demo.service.order;

import java.util.LinkedList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class OrderManager implements Consumer<Order> {

    private Map<Sell, Fulfillment<Buy>> sellOrders = new ConcurrentHashMap<>();
    private Map<Buy, Fulfillment<Sell>> buyOrders = new ConcurrentHashMap<>();

    @Override
    public synchronized void accept(Order order) {
        switch(order) {
            case Sell s -> sellOrders.put(s, new Fulfillment<>());
            case Buy b -> buyOrders.put(b, new Fulfillment<>());
            default -> throw new IllegalStateException("Unexpected value: " + order);
        }
    }

    public synchronized LinkedList<Order> getAll() {
        return new LinkedList<Order>(){{
            addAll(sellOrders.keySet());
            addAll(buyOrders.keySet());
        }};
    }

    public synchronized LinkedList<Order> getFullyFulfilled() {
        var fulfilled = new LinkedList<Order>();
        buyOrders.forEach((buy, buyFulfilledBy)->{
            sellOrders.forEach((sell, sellFulfilledBy)->{
                int buyUnFulfilled = unfulfilled(buy,buyFulfilledBy);
                int sellUnfulfilled = unfulfilled(sell,sellFulfilledBy);
                if (sellUnfulfilled == buyUnFulfilled) {
                    buyFulfilledBy.add(sell, sellUnfulfilled);
                    sellFulfilledBy.add(buy, buyUnFulfilled);
                    sellOrders.remove(sell);
                    buyOrders.remove(buy);
                    fulfilled.add(sell);
                    fulfilled.add(buy);
                } else if(buyUnFulfilled > sellUnfulfilled) {
                    buyFulfilledBy.add(sell, sellUnfulfilled);
                    sellFulfilledBy.add(buy, sellUnfulfilled);
                    sellOrders.remove(sell);
                    fulfilled.add(sell);
                } else if(sellUnfulfilled > buyUnFulfilled) {
                    buyFulfilledBy.add(sell, buyUnFulfilled);
                    sellFulfilledBy.add(buy, buyUnFulfilled);
                    buyOrders.remove(buy);
                    fulfilled.add(buy);
                }
            });
        });
        return fulfilled;
    }

    public int unfulfilled(Order order, Fulfillment<? extends Order> fulfilled) {
        return order.amount() - fulfilled.amount();
    }
}
