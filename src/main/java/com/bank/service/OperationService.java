package com.bank.service;

import com.bank.model.Operation;

import java.util.List;

public interface OperationService {

    List findByAccountId(Integer id);

    Operation findById(Integer id);

    void createOperation(Operation operation) throws Exception;
}
