package com.example.demo.service.order;

import java.util.UUID;

public sealed interface Order permits Buy, Sell {

    UUID id();
    int amount();
}
