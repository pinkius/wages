package com.webstersmalley.wages.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by: Matthew Smalley
 * Date: 06/03/14
 */
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstNames;
    private String lastName;
    private String taxCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstNames() {
        return firstNames;
    }

    public void setFirstNames(String firstNames) {
        this.firstNames = firstNames;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public Employee() {

    }

    public Employee(String firstNames, String lastName, String taxCode) {
        this.firstNames = firstNames;
        this.lastName = lastName;
        this.taxCode = taxCode;
    }

    public boolean equals(Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Employee rhs = (Employee) obj;
        return new EqualsBuilder()
                .append(firstNames, rhs.firstNames)
                .append(lastName, rhs.lastName)
                .append(taxCode, rhs.taxCode)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).
                append(firstNames).
                append(lastName).
                append(taxCode).
                toHashCode();
    }
}
