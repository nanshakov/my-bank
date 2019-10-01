package com.nanshakov.bank.controllers;

import com.nanshakov.bank.dto.ExecutionReport;
import com.nanshakov.bank.dto.Order;
import com.nanshakov.bank.services.SimpleOrderProcessor;
import com.nanshakov.bank.services.impl.qF.QFServiceImpl;
import com.nanshakov.bank.utils.OrderType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import quickfix.Message;
import quickfix.SessionID;

@RestController
public class ApiController {

    @Autowired
    private SimpleOrderProcessor orderProcessor;
    @Autowired
    private QFServiceImpl qfService;

    //http://127.0.0.1:8080/placeOrder?amount=100
    @GetMapping(value = "/placeOrder", params = {"amount"})
    public ExecutionReport placeOrder(@RequestParam(value = "amount") Optional<Long> amount) {
        return orderProcessor.process(new Order("", amount.orElse(100L), OrderType.Market));
    }

    @GetMapping(value = "/history", params = {"clientId"})
    public List<Message> history(@RequestParam(value = "clientId") SessionID clientId) {
        return qfService.getHistory().get(clientId);
    }
}
