package org.charter.democharter.repository;

import org.charter.democharter.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity, Integer> {

    List<TransactionEntity> findAllByCustomerEntity_CustomerID(Integer customerID);
}
