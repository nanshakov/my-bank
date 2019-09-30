package com.nanshakov.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Nikita Anshakov
 * @version 30.09.2019
 * @since 30.09.2019
 */
@Data
@AllArgsConstructor
public class ExecutionReport {
    boolean isSuccessful;
    long amount;
}
