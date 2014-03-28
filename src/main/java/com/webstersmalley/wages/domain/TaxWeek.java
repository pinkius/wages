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

package com.webstersmalley.wages.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * Created by: Matthew Smalley
 * Date: 28/03/14
 */
public class TaxWeek {
    private Employee employee;
    private DateTime weekCommencing;
    private String taxCode;
    private BigDecimal grossThisWeek;
    private BigDecimal cumulativeGross;
    private BigDecimal cumulativeTaxToPreviousWeek;
    private BigDecimal taxThisWeek;
    private BigDecimal netThisWeek;
    private BigDecimal cumulativeNet;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public DateTime getWeekCommencing() {
        return weekCommencing;
    }

    public void setWeekCommencing(DateTime weekCommencing) {
        this.weekCommencing = weekCommencing;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public BigDecimal getGrossThisWeek() {
        return grossThisWeek;
    }

    public void setGrossThisWeek(BigDecimal grossThisWeek) {
        this.grossThisWeek = grossThisWeek;
    }

    public BigDecimal getCumulativeGross() {
        return cumulativeGross;
    }

    public void setCumulativeGross(BigDecimal cumulativeGross) {
        this.cumulativeGross = cumulativeGross;
    }

    public BigDecimal getCumulativeTaxToPreviousWeek() {
        return cumulativeTaxToPreviousWeek;
    }

    public void setCumulativeTaxToPreviousWeek(BigDecimal cumulativeTaxToPreviousWeek) {
        this.cumulativeTaxToPreviousWeek = cumulativeTaxToPreviousWeek;
    }

    public BigDecimal getTaxThisWeek() {
        return taxThisWeek;
    }

    public void setTaxThisWeek(BigDecimal taxThisWeek) {
        this.taxThisWeek = taxThisWeek;
    }

    public BigDecimal getNetThisWeek() {
        return netThisWeek;
    }

    public void setNetThisWeek(BigDecimal netThisWeek) {
        this.netThisWeek = netThisWeek;
    }

    public BigDecimal getCumulativeNet() {
        return cumulativeNet;
    }

    public void setCumulativeNet(BigDecimal cumulativeNet) {
        this.cumulativeNet = cumulativeNet;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this).toString();
    }
}
