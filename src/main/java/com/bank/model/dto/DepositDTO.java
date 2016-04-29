package com.bank.model.dto;

import com.bank.model.Deposit;

public class DepositDTO extends OperationDTO {

    private AccountDTO to;

    private boolean closed;

    public DepositDTO(Deposit deposit) {
        super(deposit);
        setTo(new AccountDTO(deposit.getToAccount()));
        setClosed(deposit.isClosed());
    }

    public AccountDTO getTo() {
        return to;
    }

    public void setTo(AccountDTO to) {
        this.to = to;
    }

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }
}
