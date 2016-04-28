package com.bank.model.dto;

import com.bank.model.Account;

import java.util.*;
import java.util.stream.Collectors;

public class OperationsDTO {

    private List<OperationDTO> inTransfers = new ArrayList<>();

    private List<OperationDTO> outTransfers = new ArrayList<>();

    private List<OperationDTO> deposits = new ArrayList<>();

    public OperationsDTO(final Account account) {
        inTransfers.addAll(account.getInTransfers().stream().map(OperationDTO::new).collect(Collectors.toList()));
        outTransfers.addAll(account.getOutTransfers().stream().map(OperationDTO::new).collect(Collectors.toList()));
        deposits.addAll(account.getDeposits().stream().map(OperationDTO::new).collect(Collectors.toList()));
    }

    public List<OperationDTO> getOutTransfers() {
        return outTransfers;
    }

    public List<OperationDTO> getDeposits() {
        return deposits;
    }

    public List<OperationDTO> getInTransfers() {
        return inTransfers;
    }
}
