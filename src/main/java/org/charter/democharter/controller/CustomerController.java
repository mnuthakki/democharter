package org.charter.democharter.controller;

import io.swagger.annotations.ApiOperation;
import org.charter.democharter.business.model.Customer;
import org.charter.democharter.business.model.ResponseModel;
import org.charter.democharter.business.model.StatusTypeText;
import org.charter.democharter.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/{id}",
            produces = {
                    MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(value = "Retrieve Customer details for provided customer ID")
    public ResponseModel<Customer> getCustomerById(@PathVariable(name = "id") int id) {
        Customer customer = customerService.getCustomerByID(id);

        return null != customer.getCustomerID()
                ? ResponseModel.of(customer, HttpStatus.OK.value(), StatusTypeText.SUCCESS.getValue())
                : ResponseModel.of(customer, HttpStatus.NO_CONTENT.value(), StatusTypeText.FAILURE.getValue());
    }

}
