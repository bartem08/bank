package com.bank.model.dto;

import com.bank.model.Operation;

public class OperationDTO {

    private String type;

    private String date;

    private float saldo;

    private String description;

    public OperationDTO(final Operation operation) {
        setType(operation.getType());
        setDate(operation.getDate());
        setSaldo(operation.getSaldo());
        setDescription(operation.getDescription());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
