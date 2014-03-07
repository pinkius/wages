package com.webstersmalley.wages.service;

import com.webstersmalley.wages.domain.TimeSheetEntry;
import com.webstersmalley.wages.domain.TimeSheetEntryType;
import com.webstersmalley.wages.repository.EmployeeRepository;
import com.webstersmalley.wages.repository.TimeSheetEntryRepository;
import com.webstersmalley.wages.repository.TimeSheetEntryTypeRepository;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public List<TimeSheetEntry> findByEmployeeAndDateBetween(Long employeeId, DateTime start, DateTime end) {
        if (start == null) {
            if (end == null) {
                start = new DateTime().withDayOfWeek(1);
            } else {
                start = end.minusDays(7);
            }
        }
        if (end == null) {
            end = start.plusDays(7);
        }
        return timeSheetEntryRepository.findByEmployeeAndDateBetween(employeeRepository.findById(employeeId), start, end);
    }
}
