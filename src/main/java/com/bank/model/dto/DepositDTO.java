package com.bank.model.dto;

import com.bank.model.Deposit;

public class DepositDTO extends OperationDTO {

    private AccountDTO to;

    public DepositDTO(Deposit deposit) {
        super(deposit);
        setTo(new AccountDTO(deposit.getToAccount()));
    }

    public AccountDTO getTo() {
        return to;
    }

    public void setTo(AccountDTO to) {
        this.to = to;
    }
}
