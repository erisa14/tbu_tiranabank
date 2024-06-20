package com.erisasadikllari.tbu_tiranabank.models;

import lombok.Data;

@Data
public class TransactionRequest {

    private long debitAccountId;
    private String creditAccountNumber;
    private double amount;
    private String description;

}