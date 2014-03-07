package com.webstersmalley.wages.domain;

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
 * Date: 07/03/14
 */
@Entity
public class TimeSheetEntry {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "employeeId", nullable = false)
    private Employee employee;

    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime date;
    private BigDecimal hours;

    @ManyToOne
    @JoinColumn(name = "timeSheetEntryTypeId", nullable = false)
    private TimeSheetEntryType timeSheetEntryType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TimeSheetEntryType getTimeSheetEntryType() {
        return timeSheetEntryType;
    }

    public void setTimeSheetEntryType(TimeSheetEntryType timeSheetEntryType) {
        this.timeSheetEntryType = timeSheetEntryType;
    }

    public DateTime getDate() {
        return date;
    }

    public void setDate(DateTime date) {
        this.date = date;
    }

    public BigDecimal getHours() {
        return hours;
    }

    public void setHours(BigDecimal hours) {
        this.hours = hours;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public TimeSheetEntry() {

    }

    public TimeSheetEntry(TimeSheetEntryType timeSheetEntryType, DateTime date, BigDecimal hours, Employee employee) {
        this.timeSheetEntryType = timeSheetEntryType;
        this.date = date;
        this.hours = hours;
        this.employee = employee;
    }
}
