package com.bank.service.jpa;

import com.bank.model.*;
import com.bank.repository.jpa.*;
import com.bank.service.AccountManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;

import java.security.Principal;
import java.util.List;

@Service
public class AccountManagementImpl implements AccountManagement {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE,
            transactionManager = "transactionManager",
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public Account updateBalance(final Transfer transfer) {
        final Account from = transfer.getFromAccount();
        decreaseBalance(from, transfer);
        final Account to = transfer.getToAccount();
        increaseBalance(to, transfer);
        transfer.setConfirmed(true);
        transferRepository.save(transfer);
        accountRepository.save(to);
        return accountRepository.save(from);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE,
            transactionManager = "transactionManager",
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public Account updateBalance(final Principal principal, final Deposit deposit) {
        if (!checkIfDepositExists(principal)) {
            throw new RuntimeException("Has opened deposits");
        } else {
            final Account account = deposit.getToAccount();
            increaseBalance(account, deposit);
            deposit.setConfirmed(true);
            depositRepository.save(deposit);
            return accountRepository.save(account);
        }
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE,
            transactionManager = "transactionManager",
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public Account closeDeposit(final Deposit deposit) {
        final Account account = deposit.getToAccount();
        decreaseBalance(account, deposit);
        deposit.setClosed(true);
        depositRepository.save(deposit);
        return accountRepository.save(account);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE,
            transactionManager = "transactionManager",
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public Transfer saveTransfer(final Transfer transfer) {
        if (transfer.getId() != null) {
            throw new RuntimeException();
        } else {
            return transferRepository.save(transfer);
        }
    }

    @Override
    public Transfer getTransferById(Integer id) {
        return transferRepository.findOne(id);
    }

    @Override
    public Deposit getDepositById(Integer id) {
        return depositRepository.findOne(id);
    }

    @Override
    public void deleteTransfer(Integer id) {
        transferRepository.delete(id);
    }

    @Override
    public void deleteDeposit(Integer id) {
        depositRepository.delete(id);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE,
            transactionManager = "transactionManager",
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public Deposit saveDeposit(final Deposit deposit) {
        if (deposit.getId() != null) {
            throw new RuntimeException("Id must be null");
        } else {
            return depositRepository.save(deposit);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE,
            transactionManager = "transactionManager",
            propagation = Propagation.MANDATORY,
            rollbackFor = Exception.class)
    private void decreaseBalance(final Account account, final Operation operation) {
        final float actualBalance = account.getBalance();
        final float finalBalance = actualBalance - operation.getSaldo();
        if (finalBalance < 0) {
            throw new RuntimeException("Not enough money");
        } else {
            account.setBalance(finalBalance);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE,
            transactionManager = "transactionManager",
            propagation = Propagation.MANDATORY,
            rollbackFor = Exception.class)
    private void increaseBalance(final Account account, final Operation operation) {
        final float finalBalance = account.getBalance() + operation.getSaldo();
        account.setBalance(finalBalance);
    }

    private boolean checkIfDepositExists(final Principal principal) {
        List<Account> accounts = accountRepository.findByProfileInn(principal.getName());
        for (Account account: accounts) {
            List<Deposit> deposits = account.getDeposits();
            if (!deposits.isEmpty()) {
                for (Deposit deposit : deposits) {
                    if (!deposit.isClosed() && deposit.isConfirmed()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public Account createAccount(final Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAccountByProfileInn(final String userInn) {
        return accountRepository.findByProfileInn(userInn);
    }

    @Override
    public Account getAccountById(final Integer accountId) {
        if (!accountRepository.exists(accountId)) {
            throw new RuntimeException("Account does not exists");
        }
        return accountRepository.findById(accountId);
    }

}
