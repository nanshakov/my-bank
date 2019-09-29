package com.nanshakov.bank.dto;

import com.nanshakov.bank.utils.OrderType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Order {
    @NonNull
    String orderId;
    long amount;
    OrderType orderType;
}
