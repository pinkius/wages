/*
 * Copyright 2014 Webster Smalley
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.webstersmalley.fees.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * Created by: Matthew Smalley
 * Date: 01/04/14
 */
@Entity
public class PaymentScheduleElement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Resident resident;
    private String name;
    private PayerType payerType;
    private BigDecimal amount;
    private PaymentFrequency paymentFrequency;
    private LocalDate activeFrom;
    private LocalDate activeTo;

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        PaymentScheduleElement rhs = (PaymentScheduleElement) obj;
        return new EqualsBuilder()
                .append(resident, rhs.resident)
                .append(name, rhs.name)
                .append(payerType, rhs.payerType)
                .append(amount, rhs.amount)
                .append(paymentFrequency, rhs.paymentFrequency)
                .append(activeFrom, rhs.activeFrom)
                .append(activeTo, rhs.activeTo)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(resident)
                .append(name)
                .append(payerType)
                .append(amount)
                .append(paymentFrequency)
                .append(activeFrom)
                .append(activeTo)
                .toHashCode();
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this).toString();
    }

    private PaymentScheduleElement() {

    }

    public PaymentScheduleElement(Resident resident, String name, PayerType payerType, BigDecimal amount, PaymentFrequency paymentFrequency, LocalDate activeFrom, LocalDate activeTo) {
        this.resident = resident;
        this.name = name;
        this.payerType = payerType;
        this.amount = amount;
        this.paymentFrequency = paymentFrequency;
        this.activeFrom = activeFrom;
        this.activeTo = activeTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PayerType getPayerType() {
        return payerType;
    }

    public void setPayerType(PayerType payerType) {
        this.payerType = payerType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public PaymentFrequency getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(PaymentFrequency paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public LocalDate getActiveFrom() {
        return activeFrom;
    }

    public void setActiveFrom(LocalDate activeFrom) {
        this.activeFrom = activeFrom;
    }

    public LocalDate getActiveTo() {
        return activeTo;
    }

    public void setActiveTo(LocalDate activeTo) {
        this.activeTo = activeTo;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }
}
