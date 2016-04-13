package com.bank.repository.jpa;

import com.bank.model.Account;
import com.bank.model.Operation;
import com.bank.repository.OperationRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.*;
import javax.persistence.*;
import java.util.List;

@Repository
public class OperationRepositoryImpl implements OperationRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List findByAccountId(Integer id) {
        final Query query = em.createNativeQuery("SELECT * FROM operations WHERE account_id=:id");
        query.setParameter("id", id);
        return query.getResultList();
    }

    @Override
    public Operation findById(Integer id) {
        return em.find(Operation.class, id);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRED,
            transactionManager = "transactionManager",
            rollbackFor = Exception.class)
    public void createOperation(Operation operation) throws Exception {
        em.persist(operation);
        Account account = em.find(Account.class, operation.getAccount().getId());
        account.setBalance(operation);
        em.merge(account);
    }

}
