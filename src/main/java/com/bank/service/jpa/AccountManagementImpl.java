package com.bank.service.jpa;

import com.bank.model.*;
import com.bank.repository.jpa.*;
import com.bank.service.AccountManagement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.*;
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
    public void updateBalance(final Transfer transfer) {
        final Account from = transfer.getFromAccount();
        final float fromBalance = from.getBalance() - transfer.getSaldo();
        if (fromBalance < 0) {
            throw new RuntimeException("Not enough money");
        } else {
            from.setBalance(fromBalance);
            accountRepository.save(from);
            final Account to = transfer.getToAccount();
            to.setBalance(to.getBalance() + transfer.getSaldo());
            accountRepository.save(to);
            createOperation(transfer);
        }
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE,
            transactionManager = "transactionManager",
            propagation = Propagation.REQUIRED,
            rollbackFor = Exception.class)
    public void updateBalance(Deposit deposit) {
        final Account account = deposit.getToAccount();
        final float balance = account.getBalance();
        if (balance < 0) {
            throw new RuntimeException("Balance must be > 0 before deposit");
        } else {
            account.setBalance(balance + deposit.getSaldo());
            accountRepository.save(account);
            createOperation(deposit);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE,
            transactionManager = "transactionManager",
            propagation = Propagation.MANDATORY,
            rollbackFor = Exception.class)
    private void createOperation(Transfer transfer) {
        if (transfer.getId() != null) {
            throw new RuntimeException();
        } else {
            transferRepository.save(transfer);
        }
    }

    @Transactional(isolation = Isolation.SERIALIZABLE,
            transactionManager = "transactionManager",
            propagation = Propagation.MANDATORY,
            rollbackFor = Exception.class)
    private void createOperation(Deposit deposit) {
        if (deposit.getId() != null) {
            throw new RuntimeException();
        } else {
            depositRepository.save(deposit);
        }
    }

    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> getAccountByProfileInn(String userInn) {
        return accountRepository.findByProfileInn(userInn);
    }

    @Override
    public Account getAccountById(Integer accountId) {
        return accountRepository.findById(accountId);
    }


    @Override
    public List<Operation> getDepositsByAccountId(Integer accountId) {
        return depositRepository.findByToAccountId(accountId);
    }

    @Override
    public List<Operation> getTransfersInByAccountId(Integer accountId) {
        return transferRepository.findByToAccountId(accountId);
    }

    @Override
    public List<Operation> getTransfersOutByAccountId(Integer accountId) {
        return transferRepository.findByFromAccountId(accountId);
    }
}
