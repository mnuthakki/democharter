package org.charter.democharter.business.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rewards {

    private Customer customer;
    private Map<String, Integer> rewardPointsMap;
    private List<Transaction> transactionList;

}
