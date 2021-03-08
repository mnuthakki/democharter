package org.charter.democharter.service;

import lombok.extern.log4j.Log4j2;
import org.charter.democharter.business.model.Transaction;
import org.charter.democharter.entity.TransactionEntity;
import org.charter.democharter.mapper.entity.TransactionEntityMapper;
import org.charter.democharter.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Log4j2
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    TransactionEntityMapper transactionEntityMapper;

    @Transactional
    @Modifying
    public Optional<Integer> saveTransaction(Transaction transaction) {
        TransactionEntity transactionEntity;
        try {
            transactionEntity = transactionRepository.save(transactionEntityMapper.map(transaction));
        } catch (Exception e) {
            log.error("Exception occurred saving transaction information for customer with ID: " + transaction.getCustomerID()
                    + " with message : " + e.getMessage());
            throw e;
        }
        return Optional.ofNullable(transactionEntity.getTransactionID());

    }
}
