package com.bank.service.jpa;

import com.bank.model.Operation;
import com.bank.repository.OperationRepository;
import com.bank.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public List findByAccountId(Integer id) {
        return operationRepository.findByAccountId(id);
    }

    @Override
    public Operation findById(Integer id) {
        return operationRepository.findById(id);
    }

    @Override
    public void createOperation(Operation operation) throws Exception {
        operationRepository.createOperation(operation);
    }
}
