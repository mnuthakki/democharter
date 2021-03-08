package org.charter.democharter.constants;

import org.charter.democharter.entity.TransactionEntity;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.function.Predicate;

public class DemoConstants {

    public static final BigDecimal VALUE_50 = new BigDecimal(String.valueOf(50));
    public static final BigDecimal VALUE_100 = new BigDecimal(String.valueOf(100));
    public static final Date THREE_MONTHS_DATE = Date.from(ZonedDateTime.now().minusMonths(3).toInstant());

    public static final Predicate<TransactionEntity> lessThan50 = (TransactionEntity transactionEntity) ->
            transactionEntity.getTransactionAmount().compareTo(new BigDecimal(String.valueOf(50))) >= 0;

    public final static Predicate<BigDecimal> lessThan100 = (BigDecimal amount) ->
            amount.compareTo(VALUE_50) >= 0 && amount.compareTo(VALUE_100) < 0;

    public final static Predicate<BigDecimal> greaterThan100 = (BigDecimal amount) ->
            amount.compareTo(VALUE_100) >= 0;

}
