package org.charter.democharter.controller;

import io.swagger.annotations.ApiOperation;
import org.charter.democharter.business.model.ResponseModel;
import org.charter.democharter.business.model.StatusTypeText;
import org.charter.democharter.business.model.Transaction;
import org.charter.democharter.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(produces = {
            MediaType.APPLICATION_JSON_VALUE
    })
    @ApiOperation(value = "Insert transaction for a particular User")
    public ResponseModel<Object> saveTransactions(@Valid @RequestBody Transaction transaction) {
        Optional<Integer> id = transactionService.saveTransaction(transaction);
        return id.<ResponseModel<Object>>map(integer -> ResponseModel.of(integer, HttpStatus.OK.value(), StatusTypeText.SUCCESS.getValue()))
                .orElseGet(() -> ResponseModel.of("", HttpStatus.NO_CONTENT.value(), StatusTypeText.FAILURE.getValue()));

    }
}
