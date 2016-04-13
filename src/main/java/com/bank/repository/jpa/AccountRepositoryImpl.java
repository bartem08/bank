package com.bank.repository.jpa;


import com.bank.model.*;
import com.bank.repository.AccountRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.*;
import javax.persistence.*;
import java.util.List;

@Repository
public class AccountRepositoryImpl implements AccountRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRED,
            transactionManager = "transactionManager",
            rollbackFor = Exception.class)
    public void createAccount(Account account) throws Exception {
        em.persist(account);
    }

    @Override
    public Account findById(Integer id) {
        return em.find(Account.class, id);
    }

    @Override
    public List findByUserId(Integer id) {
        Query query = em.createNativeQuery("SELECT * FROM accounts WHERE user_id=:id", Account.class);
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public void deleteAccount(Integer id) throws Exception {
        em.remove(id);
    }

}
