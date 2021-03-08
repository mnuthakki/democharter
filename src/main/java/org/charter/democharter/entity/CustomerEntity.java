package org.charter.democharter.entity;

import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "customer")
@NamedQuery(name = "CustomerEntity.findAll", query = "SELECT c FROM CustomerEntity c")
@Data
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 2720952339419156217L;

    @Id
    @Column(name = "Customer_ID")
    private Integer customerID;

    @Basic
    @Column(name = "first_name")
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "Zip_Code")
    private String zipCode;

}
