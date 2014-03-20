package com.webstersmalley.wages.domain;

import org.joda.time.DateTime;

/**
 * Created by: Matthew Smalley
 * Date: 20/03/14
 */
public class TimeSheetRow {
    private Long employeeId;
    private DateTime weekCommencing;
    private TimeSheetEntry[] entries;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public DateTime getWeekCommencing() {
        return weekCommencing;
    }

    public void setWeekCommencing(DateTime weekCommencing) {
        this.weekCommencing = weekCommencing;
    }

    public TimeSheetEntry[] getEntries() {
        return entries;
    }

    public void setEntries(TimeSheetEntry[] entries) {
        this.entries = entries;
    }
}
