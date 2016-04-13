package com.bank.repository.jpa;

import com.bank.model.*;
import com.bank.repository.UserRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.*;

import javax.persistence.*;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE,
            rollbackFor = Exception.class,
            transactionManager = "transactionManager",
            propagation = Propagation.REQUIRED)
    public void createUser(User user) throws Exception {
        em.persist(user);
        if (applyRole(user.getInn()) <= 0) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public User findByInn(final String inn) {
        Query query = em.createNativeQuery("SELECT * FROM users WHERE inn=:inn", User.class);
        query.setParameter("inn", inn);
        return (User) query.getSingleResult();
    }

    @Override
    public User findById(Integer id) {
        return em.find(User.class, id);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE,
            rollbackFor = Exception.class,
            transactionManager = "transactionManager",
            propagation = Propagation.REQUIRED)
    public void updateUser(final User user) throws Exception {
        em.merge(user);
    }

    @Override
    public List findUserAccounts(Integer userId) {
        Query query = em.createNativeQuery("SELECT * FROM accounts WHERE user_id=:id", Account.class);
        query.setParameter("id", userId);
        return query.getResultList();
    }

    @Transactional(propagation = Propagation.MANDATORY,
            transactionManager = "transactionManager")
    private int applyRole(String inn) {
        Query query = em.createNativeQuery("INSERT INTO user_roles (id, inn, role) VALUES (NULL, ?, 'ROLE_USER')");
        query.setParameter(1, inn);
        return query.executeUpdate();
    }

}
