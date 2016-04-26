package com.bank.model.dto;

import com.bank.model.Operation;

public class OperationDTO {

    private Integer id;

    private float saldo;

    private String date;

    private String description;

    public OperationDTO(Operation operation) {
        setId(operation.getId());
        setDate(operation.getDate());
        setSaldo(operation.getSaldo());
        setDescription(operation.getDescription());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
