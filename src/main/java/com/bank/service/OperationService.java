package com.bank.service;

import com.bank.model.Operation;

import java.util.List;

public interface OperationService {

    List<Operation> getDepositsByAccountId(Integer accountId);

    List<Operation> getTransfersInByAccountId(Integer accountId);

    List<Operation> getTransfersOutByAccountId(Integer accountId);

}
