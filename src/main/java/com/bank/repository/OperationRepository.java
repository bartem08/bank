package com.bank.repository;

import com.bank.model.Operation;
import java.util.List;

public interface OperationRepository {

    List findByAccountId(Integer id);

    Operation findById(Integer id);

    void createOperation(Operation operation) throws Exception;

}
