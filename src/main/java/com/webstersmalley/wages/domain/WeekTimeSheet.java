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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

/**
 * Created by: Matthew Smalley
 * Date: 23/03/14
 */
@Entity
public class WeekTimeSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "timeSheetEntryTypeId", nullable = false)
    private TimeSheetEntryType timeSheetEntryType;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime weekCommencing;

    private BigDecimal mondayHours;
    private BigDecimal tuesdayHours;
    private BigDecimal wednesdayHours;
    private BigDecimal thursdayHours;
    private BigDecimal fridayHours;
    private BigDecimal saturdayHours;
    private BigDecimal sundayHours;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TimeSheetEntryType getTimeSheetEntryType() {
        return timeSheetEntryType;
    }

    public void setTimeSheetEntryType(TimeSheetEntryType timeSheetEntryType) {
        this.timeSheetEntryType = timeSheetEntryType;
    }

    public DateTime getWeekCommencing() {
        return weekCommencing;
    }

    public void setWeekCommencing(DateTime weekCommencing) {
        this.weekCommencing = weekCommencing;
    }

    public BigDecimal getMondayHours() {
        return mondayHours;
    }

    public void setMondayHours(BigDecimal mondayHours) {
        this.mondayHours = mondayHours;
    }

    public BigDecimal getTuesdayHours() {
        return tuesdayHours;
    }

    public void setTuesdayHours(BigDecimal tuesdayHours) {
        this.tuesdayHours = tuesdayHours;
    }

    public BigDecimal getWednesdayHours() {
        return wednesdayHours;
    }

    public void setWednesdayHours(BigDecimal wednesdayHours) {
        this.wednesdayHours = wednesdayHours;
    }

    public BigDecimal getThursdayHours() {
        return thursdayHours;
    }

    public void setThursdayHours(BigDecimal thursdayHours) {
        this.thursdayHours = thursdayHours;
    }

    public BigDecimal getFridayHours() {
        return fridayHours;
    }

    public void setFridayHours(BigDecimal fridayHours) {
        this.fridayHours = fridayHours;
    }

    public BigDecimal getSaturdayHours() {
        return saturdayHours;
    }

    public void setSaturdayHours(BigDecimal saturdayHours) {
        this.saturdayHours = saturdayHours;
    }

    public BigDecimal getSundayHours() {
        return sundayHours;
    }

    public void setSundayHours(BigDecimal sundayHours) {
        this.sundayHours = sundayHours;
    }

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
        WeekTimeSheet rhs = (WeekTimeSheet) obj;
        return new EqualsBuilder()
                .append(employee, rhs.employee)
                .append(timeSheetEntryType, rhs.timeSheetEntryType)
                .append(weekCommencing, rhs.weekCommencing)
                .append(mondayHours, rhs.mondayHours)
                .append(tuesdayHours, rhs.tuesdayHours)
                .append(wednesdayHours, rhs.wednesdayHours)
                .append(thursdayHours, rhs.thursdayHours)
                .append(fridayHours, rhs.fridayHours)
                .append(saturdayHours, rhs.saturdayHours)
                .append(sundayHours, rhs.sundayHours)
                .isEquals();
    }

    public int hashCode() {
        return new HashCodeBuilder(17, 37).
                append(employee).
                append(timeSheetEntryType).
                append(weekCommencing).
                append(mondayHours).
                append(tuesdayHours).
                append(wednesdayHours).
                append(thursdayHours).
                append(fridayHours).
                append(saturdayHours).
                append(sundayHours).
                toHashCode();
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this).toString();
    }
}
