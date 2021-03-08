package org.charter.democharter.business.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    private int customerID;

    @NotNull
    @Builder.Default
    private BigDecimal transactionAmount = new BigDecimal(String.valueOf(0));

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "US/Eastern")
    private Date transactionDate;

}
