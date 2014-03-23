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

package com.webstersmalley.wages.service;

import com.webstersmalley.wages.domain.Employee;
import com.webstersmalley.wages.domain.TimeSheetEntry;
import com.webstersmalley.wages.domain.TimeSheetRow;
import com.webstersmalley.wages.repository.EmployeeRepository;
import com.webstersmalley.wages.repository.TimeSheetEntryRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
@Service("timeSheetEntryService")
public class TimeSheetEntryService {
    @Resource
    private TimeSheetEntryRepository timeSheetEntryRepository;

    @Resource
    private EmployeeRepository employeeRepository;

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/mm/yyyy");

    private final static int DAYS_IN_WEEK = 7;

    public List<TimeSheetEntry> findAll() {
        return timeSheetEntryRepository.findAll();
    }

    public TimeSheetEntry save(TimeSheetEntry timeSheetEntry) {
        return timeSheetEntryRepository.save(timeSheetEntry);
    }

    public List<TimeSheetRow> getTimeSheetByEmployeeAndDateRange(Long employeeId, DateTime start, DateTime end) {
        List<TimeSheetRow> timeSheetRows = new ArrayList<>();

        DateTime currentStart;
        DateTime realEnd;
        if (start == null) {
            if (end == null) {
                currentStart = new DateTime().withDayOfWeek(DateTimeConstants.MONDAY);
            } else {
                currentStart = end.minusWeeks(1).withDayOfWeek(DateTimeConstants.MONDAY);
            }
        } else {
            currentStart = start.withDayOfWeek(DateTimeConstants.MONDAY);
        }
        if (end == null) {
            realEnd = currentStart.withDayOfWeek(DateTimeConstants.SUNDAY);
        } else {
            realEnd = end.withDayOfWeek(DateTimeConstants.SUNDAY);
        }

        while (currentStart.isBefore(realEnd)) {
            DateTime currentEnd = currentStart.withDayOfWeek(DateTimeConstants.SUNDAY);
            List<TimeSheetEntry> list = timeSheetEntryRepository.findByEmployeeAndDateBetween(employeeRepository.findById(employeeId), currentStart, currentEnd);
            if (list.size() > 0) {
                TimeSheetRow row = new TimeSheetRow();
                row.setEmployeeId(employeeId);
                row.setWeekCommencing(currentStart);
                TimeSheetEntry[] entries = new TimeSheetEntry[DAYS_IN_WEEK];
                row.setEntries(entries);
                for (TimeSheetEntry entry : list) {
                    entries[entry.getDate().getDayOfWeek() - 1] = entry;
                }
                timeSheetRows.add(row);
            }
            currentStart = currentStart.plusWeeks(1);
        }
        return timeSheetRows;
    }


    public void saveTimeSheet(Long employeeId, String weekCommencingString, BigDecimal hours0, BigDecimal hours1, BigDecimal hours2, BigDecimal hours3, BigDecimal hours4, BigDecimal hours5, BigDecimal hours6) {
        DateTime weekCommencingDate = formatter.parseDateTime(weekCommencingString);
        Employee employee = employeeRepository.findById(employeeId);
        for (TimeSheetEntry entry : timeSheetEntryRepository.findByEmployeeAndDateBetween(employee, weekCommencingDate, weekCommencingDate.plusWeeks(0))) {
            timeSheetEntryRepository.delete(entry);
        }
        timeSheetEntryRepository.save(new TimeSheetEntry(null, weekCommencingDate, hours0, employee));
        timeSheetEntryRepository.save(new TimeSheetEntry(null, weekCommencingDate.plusDays(1), hours1, employee));
        timeSheetEntryRepository.save(new TimeSheetEntry(null, weekCommencingDate.plusDays(2), hours2, employee));
        timeSheetEntryRepository.save(new TimeSheetEntry(null, weekCommencingDate.plusDays(3), hours3, employee));
        timeSheetEntryRepository.save(new TimeSheetEntry(null, weekCommencingDate.plusDays(4), hours4, employee));
        timeSheetEntryRepository.save(new TimeSheetEntry(null, weekCommencingDate.plusDays(5), hours5, employee));
        timeSheetEntryRepository.save(new TimeSheetEntry(null, weekCommencingDate.plusDays(6), hours6, employee));
    }
}
