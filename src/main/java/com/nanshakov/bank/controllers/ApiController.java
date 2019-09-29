package com.nanshakov.bank.controllers;

import com.nanshakov.bank.dto.Order;
import com.nanshakov.bank.services.SimpleOrderProcessor;
import com.nanshakov.bank.services.impl.qF.QFServiceImpl;
import com.nanshakov.bank.utils.OrderType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import quickfix.SessionID;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private SimpleOrderProcessor orderProcessor;
    @Autowired
    private QFServiceImpl qfService;

    @GetMapping(value = "/placeOrder", params = {"amount"})
    public Boolean placeOrder(@RequestParam(value = "amount") long amount) {
        return orderProcessor.process(new Order("", amount, OrderType.Market));
    }

    @GetMapping(value = "/history", params = {"clientId"})
    public List history(@RequestParam(value = "clientId") SessionID clientId) {
        return qfService.getHistory().get(clientId);
    }
}
