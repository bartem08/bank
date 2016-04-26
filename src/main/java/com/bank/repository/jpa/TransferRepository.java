package com.bank.repository.jpa;

import com.bank.model.Operation;
import com.bank.model.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Integer> {

    List<Operation> findByToAccountId(Integer id);

    List<Operation> findByFromAccountId(Integer id);

}
