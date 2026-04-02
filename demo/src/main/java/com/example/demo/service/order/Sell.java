package com.example.demo.service.order;

import java.util.UUID;

public record Sell(int amount, UUID id) implements Order {
}
