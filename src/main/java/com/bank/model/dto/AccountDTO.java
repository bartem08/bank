package com.bank.model.dto;

import com.bank.model.Account;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AccountDTO {

    private int id;

    private float balance;

    private List<OperationDTO> transferIn = new ArrayList<>();

    private List<OperationDTO> transferOut = new ArrayList<>();

    private List<OperationDTO> deposits = new ArrayList<>();

    public AccountDTO(Account account) {
        setId(account.getId());
        setBalance(account.getBalance());
        transferIn.addAll(account.getInTransfers().stream().map(OperationDTO::new).collect(Collectors.toList()));
        transferOut.addAll(account.getOutTransfers().stream().map(OperationDTO::new).collect(Collectors.toList()));
        deposits.addAll(account.getDeposits().stream().map(OperationDTO::new).collect(Collectors.toList()));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public List<OperationDTO> getTransferIn() {
        return transferIn;
    }

    public void setTransferIn(List<OperationDTO> transferIn) {
        this.transferIn = transferIn;
    }

    public List<OperationDTO> getTransferOut() {
        return transferOut;
    }

    public void setTransferOut(List<OperationDTO> transferOut) {
        this.transferOut = transferOut;
    }

    public List<OperationDTO> getDeposits() {
        return deposits;
    }

    public void setDeposits(List<OperationDTO> deposits) {
        this.deposits = deposits;
    }
}
