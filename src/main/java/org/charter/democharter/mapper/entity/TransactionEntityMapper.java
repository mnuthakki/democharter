package org.charter.democharter.mapper.entity;

import org.charter.democharter.business.model.Transaction;
import org.charter.democharter.entity.CustomerEntity;
import org.charter.democharter.entity.TransactionEntity;
import org.charter.democharter.mapper.BaseMapper;
import org.springframework.stereotype.Component;

@Component
public class TransactionEntityMapper implements BaseMapper<Transaction, TransactionEntity> {

    @Override
    public TransactionEntity map(Transaction transaction) {
        TransactionEntity transactionEntity = new TransactionEntity();
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerID(transaction.getCustomerID());
        transactionEntity.setCustomerEntity(customerEntity);
        transactionEntity.setTransactionAmount(transaction.getTransactionAmount());
        transactionEntity.setTransactionDate(transaction.getTransactionDate());
        return transactionEntity;
    }
}

