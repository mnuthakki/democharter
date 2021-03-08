package org.charter.democharter.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "transaction")
@NamedQuery(name = "TransactionEntity.findAll", query = "SELECT transaction FROM TransactionEntity transaction")
@Data
public class TransactionEntity {

    @Id
    @Column(name = "Transaction_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionID;

    @Column(name = "Transaction_Amount")
    private BigDecimal transactionAmount;

    @Column(name = "Transaction_Date")
    //@Temporal(value = TemporalType.DATE)
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

}
