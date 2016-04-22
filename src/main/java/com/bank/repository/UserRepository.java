package com.bank.repository;

import com.bank.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByInn(String inn);

    User findById(Integer id);

}
