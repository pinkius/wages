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

import com.webstersmalley.wages.domain.WeekTimeSheet;
import com.webstersmalley.wages.repository.EmployeeRepository;
import com.webstersmalley.wages.repository.TimeSheetEntryTypeRepository;
import com.webstersmalley.wages.repository.WeekTimeSheetRepository;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
@Service("weekTimeSheetService")
public class WeekTimeSheetService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private WeekTimeSheetRepository weekTimeSheetRepository;

    @Resource
    private EmployeeRepository employeeRepository;

    @Resource
    private TimeSheetEntryTypeRepository timeSheetEntryTypeRepository;

    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/mm/yyyy");

    private final static int DAYS_IN_WEEK = 7;

    public WeekTimeSheet save(WeekTimeSheet weekTimeSheet) {
        logger.info("Saving timesheet: {}", weekTimeSheet);
        return weekTimeSheetRepository.save(weekTimeSheet);
    }

    public List<WeekTimeSheet> findByEmployeeAndWeekCommencingBetween(Long employeeId, DateTime start, DateTime end) {
        List<WeekTimeSheet> timeSheetRows = new ArrayList<>();

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
        logger.info("Returning with: {}", weekTimeSheetRepository.findByEmployeeAndWeekCommencingBetween(employeeRepository.findById(employeeId), currentStart.withTimeAtStartOfDay(), realEnd.withTimeAtStartOfDay()));
        return weekTimeSheetRepository.findByEmployeeAndWeekCommencingBetween(employeeRepository.findById(employeeId), currentStart.withTimeAtStartOfDay(), realEnd.withTimeAtStartOfDay());
    }


    public void saveTimeSheet(WeekTimeSheet weekTimeSheet) {
        if (weekTimeSheet.getTimeSheetEntryType() == null) {
            weekTimeSheet.setTimeSheetEntryType(timeSheetEntryTypeRepository.findAll().get(0));
        }
        weekTimeSheetRepository.save(weekTimeSheet);
    }

    public List<WeekTimeSheet> findAll() {
        return weekTimeSheetRepository.findAll();
    }
}
