package com.nanshakov.bank.services;

import com.nanshakov.bank.dto.Order;

public interface SimpleOrderProcessor {
    boolean process(Order order);
}
