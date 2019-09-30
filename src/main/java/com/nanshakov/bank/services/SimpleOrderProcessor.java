package com.nanshakov.bank.services;

import com.nanshakov.bank.dto.ExecutionReport;
import com.nanshakov.bank.dto.Order;

public interface SimpleOrderProcessor {
    ExecutionReport process(Order order);
}
