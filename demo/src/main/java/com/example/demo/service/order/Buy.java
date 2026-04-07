package com.example.demo.service.order;

import java.util.UUID;

public record Buy(int amount, UUID id) implements Order {
}
