package com.nanshakov.bank.services.impl;

import com.nanshakov.bank.dto.Order;
import com.nanshakov.bank.services.SimpleOrderProcessor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SimpleOrderProcessorImpl implements SimpleOrderProcessor {
    @Override
    public boolean process(Order order) {
        return false;
    }
}
