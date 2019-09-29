package com.nanshakov.bank.controllers;

import com.nanshakov.bank.dto.Order;
import com.nanshakov.bank.services.SimpleOrderProcessor;
import com.nanshakov.bank.utils.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private SimpleOrderProcessor orderProcessor;

    @GetMapping(value = "placeOrder", params = {"amount"})
    public Boolean placeOrder(@RequestParam(value = "amount") long amount) {
        return orderProcessor.process(new Order("", amount, OrderType.Market));
    }
}
