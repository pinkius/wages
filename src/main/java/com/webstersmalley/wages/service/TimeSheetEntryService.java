package com.webstersmalley.wages.service;

import com.webstersmalley.wages.domain.TimeSheetEntry;
import com.webstersmalley.wages.domain.TimeSheetRow;
import com.webstersmalley.wages.repository.EmployeeRepository;
import com.webstersmalley.wages.repository.TimeSheetEntryRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public List<TimeSheetEntry> findAll() {
        return timeSheetEntryRepository.findAll();
    }

    public TimeSheetEntry save(TimeSheetEntry timeSheetEntry) {
        return timeSheetEntryRepository.save(timeSheetEntry);
    }

    public List<TimeSheetRow> getTimeSheetByEmployeeAndDateRange(Long employeeId, DateTime start, DateTime end) {
        List<TimeSheetRow> timeSheetRows = new ArrayList<TimeSheetRow>();

        DateTime currentStart;
        DateTime realEnd;
        if (start == null) {
            if (end == null) {
                currentStart = new DateTime().withDayOfWeek(DateTimeConstants.MONDAY);
            } else {
                currentStart = end.minusDays(7).withDayOfWeek(DateTimeConstants.MONDAY);
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
                TimeSheetEntry[] entries = new TimeSheetEntry[7];
                row.setEntries(entries);
                for (TimeSheetEntry entry : list) {
                    entries[entry.getDate().getDayOfWeek()] = entry;
                }
                timeSheetRows.add(row);
            }
            currentStart = currentStart.plusDays(7);
        }
        return timeSheetRows;
    }
}
