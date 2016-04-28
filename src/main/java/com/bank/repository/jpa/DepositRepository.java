package com.bank.repository.jpa;

import com.bank.model.Deposit;
import com.bank.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Integer> {

    Deposit findByToAccountId(Integer accountId);


}
