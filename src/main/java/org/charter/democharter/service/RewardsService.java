package org.charter.democharter.service;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.charter.democharter.business.model.Customer;
import org.charter.democharter.business.model.Rewards;
import org.charter.democharter.business.model.Transaction;
import org.charter.democharter.entity.TransactionEntity;
import org.charter.democharter.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Month;
import java.time.ZoneId;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static org.charter.democharter.constants.DemoConstants.THREE_MONTHS_DATE;
import static org.charter.democharter.constants.DemoConstants.VALUE_100;
import static org.charter.democharter.constants.DemoConstants.VALUE_50;
import static org.charter.democharter.constants.DemoConstants.greaterThan100;
import static org.charter.democharter.constants.DemoConstants.lessThan100;
import static org.charter.democharter.constants.DemoConstants.lessThan50;

@Service
@Log4j2
public class RewardsService {

    @Autowired
    private TransactionRepository transactionRepository;


    @Transactional(readOnly = true)
    public Rewards getRewardsByCustomerID(Integer customerID) {
        Rewards rewards;
        try {
            List<TransactionEntity> transactionEntities = transactionRepository.findAllByCustomerEntity_CustomerID(customerID);
            rewards = new Rewards();

            //Get Customer Details
            rewards.setCustomer(transactionEntities.stream()
                    .map(TransactionEntity::getCustomerEntity)
                    .findFirst()
                    .map(customerEntity -> new Customer(customerEntity.getCustomerID(),
                            customerEntity.getFirstName(), customerEntity.getLastName(), customerEntity.getZipCode()))
                    .orElseGet(null));

            //Set TransactionList per User
            setTransactionList(transactionEntities, rewards);

            //Filter Last 3 months data
            Map<Month, List<TransactionEntity>> lastThreeMonthData = perMonthData(transactionEntities);

            calculatePoints(lastThreeMonthData, rewards);
        } catch (Exception e) {
            log.error("Exception occurred retrieving the rewards information for Customer ID: " + customerID + " with message : " + e.getMessage());
            throw e;
        }
        return rewards;
    }

    @Transactional(readOnly = true)
    public List<Rewards> getAllRewards() {
        List<Rewards> rewardsList;

        try {
            List<TransactionEntity> transactionEntities = transactionRepository.findAll();

            //Creating a map of Customers filtering less than $50 transactions
            Map<Integer, List<TransactionEntity>> uniqueCustomerData = transactionEntities.stream()
                    .filter(lessThan50)
                    .filter(transactionEntity -> transactionEntity.getTransactionDate().after(THREE_MONTHS_DATE))
                    .collect(Collectors.groupingBy(t -> t.getCustomerEntity().getCustomerID(),
                            TreeMap::new, Collectors.toList()));

            //Creating rewards list with customer information
            rewardsList = uniqueCustomerData.values()
                    .stream()
                    .flatMap(Collection::parallelStream)
                    .map(TransactionEntity::getCustomerEntity)
                    .distinct()
                    .map(customerEntity -> new Customer(customerEntity.getCustomerID(),
                            customerEntity.getFirstName(), customerEntity.getLastName(), customerEntity.getZipCode()))
                    .map(customer -> Rewards.builder().customer(customer).build())
                    .collect(Collectors.toList());

            //Calculating rewards points
            uniqueCustomerData.forEach((key, value) -> {
                Map<Month, List<TransactionEntity>> lastThreeMonthData = perMonthData(value);
                Optional<Rewards> reward = rewardsList.stream()
                        .filter(rewards -> rewards.getCustomer().getCustomerID().equals(key))
                        .findFirst();
                //Set TransactionList per User
                setTransactionList(value, reward.get());
                reward.ifPresent(rewards -> calculatePoints(lastThreeMonthData, rewards));
            });
        } catch (Exception e) {
            log.error("Exception occurred retrieving the rewards information for all Customers" + " with message : " + e.getMessage());
            throw e;
        }
        return rewardsList;
    }

    private static int getPoints(BigDecimal amount) {
        amount = amount.setScale(0, RoundingMode.CEILING);
        int points = 0;
        if (lessThan100.test(amount)) {
            points = amount.subtract(VALUE_50).intValue();
        } else if (greaterThan100.test(amount)) {
            points = amount.subtract(VALUE_100).intValue() * 2;
            points = points + 50;
        }
        return points;
    }

    private static Map<Month, List<TransactionEntity>> perMonthData(List<TransactionEntity> value) {

        return value.stream()
                .filter(lessThan50)
                .filter(transactionEntity -> transactionEntity.getTransactionDate().after(THREE_MONTHS_DATE))
                .collect(Collectors.groupingBy(t -> t.getTransactionDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getMonth(),
                        TreeMap::new, Collectors.toList()));
    }

    private void calculatePoints(@NonNull Map<Month, List<TransactionEntity>> lastThreeMonthData, @NonNull Rewards rewards) {
        //Calculate total points
        HashMap<String, Integer> rewardPointsMap = new HashMap<>();
        rewardPointsMap.put("Total Points", lastThreeMonthData.values().stream()
                .flatMap(Collection::stream)
                .map(TransactionEntity::getTransactionAmount)
                .mapToInt(RewardsService::getPoints)
                .sum());

        rewards.setRewardPointsMap(rewardPointsMap);

        //Calculate Per Month Points
        for (Map.Entry<Month, List<TransactionEntity>> monthData : lastThreeMonthData.entrySet()) {
            rewards.getRewardPointsMap().put(monthData.getKey().toString(),
                    monthData.getValue()
                            .stream()
                            .map(TransactionEntity::getTransactionAmount)
                            .mapToInt(RewardsService::getPoints).sum());
        }

    }

    private void setTransactionList(@NonNull List<TransactionEntity> transactionEntities, @NonNull Rewards rewards) {
        List<Transaction> transactionList = transactionEntities.stream()
                .map(transaction -> Transaction.builder().transactionDate(transaction.getTransactionDate())
                        .transactionAmount(transaction.getTransactionAmount())
                        .build())
                .collect(Collectors.toList());
        rewards.setTransactionList(transactionList);
    }


}
