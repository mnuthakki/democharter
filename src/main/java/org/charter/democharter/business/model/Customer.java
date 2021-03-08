package org.charter.democharter.business.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NonNull
public class Customer {

    private Integer customerID;
    private String firstName;
    private String lastName;
    private String zipCode;
}
