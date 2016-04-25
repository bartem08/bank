package com.bank.repository;

import com.bank.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

    Profile findByInn(String inn);

    Profile findById(Integer id);

}
