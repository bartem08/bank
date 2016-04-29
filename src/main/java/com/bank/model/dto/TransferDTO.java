package com.bank.model.dto;

import com.bank.model.Transfer;

public class TransferDTO extends OperationDTO {

    private AccountDTO from;

    private AccountDTO to;

    public TransferDTO(Transfer transfer) {
        super(transfer);
        setFrom(new AccountDTO(transfer.getFromAccount()));
        setTo(new AccountDTO(transfer.getToAccount()));
    }

    public AccountDTO getFrom() {
        return from;
    }

    public void setFrom(AccountDTO from) {
        this.from = from;
    }

    public AccountDTO getTo() {
        return to;
    }

    public void setTo(AccountDTO to) {
        this.to = to;
    }
}
