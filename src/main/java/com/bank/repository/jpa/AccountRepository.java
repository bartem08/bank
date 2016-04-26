package com.bank.repository.jpa;

import com.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    List<Account> findByProfileInn(String userInn);

    Account findById(Integer id);

}
