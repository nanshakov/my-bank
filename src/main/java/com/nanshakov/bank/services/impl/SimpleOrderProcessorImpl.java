package com.nanshakov.bank.services.impl;

import com.nanshakov.bank.dto.Order;
import com.nanshakov.bank.services.SimpleOrderProcessor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
public class SimpleOrderProcessorImpl implements SimpleOrderProcessor {
    @Override
    public boolean process(Order order) {
        log.info("{} order {} with amount {} was processed", order.getOrderType(), order.getOrderId(), order.getAmount());
        return true;
    }
}
