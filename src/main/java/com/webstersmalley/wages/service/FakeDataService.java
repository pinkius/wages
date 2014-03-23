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
import com.webstersmalley.wages.domain.TimeSheetEntryType;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
@Service
public class FakeDataService {
    @Resource
    private EmployeeService employeeService;

    @Resource
    private TimeSheetEntryTypeService timeSheetEntryTypeService;

    @Resource
    private TimeSheetEntryService timeSheetEntryService;

    private static final String[] FIRST_NAMES = {"Madeleine", "Amelia", "Oscar"};
    private static final String[] LAST_NAMES = {"Turnbull", "Gray", "Barlow"};
    private static final String[] TAX_CODES = {"117L", "028L", "123L"};
    private static final String[] RATE_NAMES = {"Standard Day Rate", "Standard Night Rate", "Special Day Rate", "Overtime", "Weekend", "Holiday"};
    private static final String[] RATES = {"100.00", "75.00", "10.00", "210.00"};

    private boolean dataAdded = false;

    private String getRandomString(String[] selection) {
        return selection[(int) (Math.random() * selection.length)];
    }

    private Employee createEmployee() {
        return employeeService.save(new Employee(getRandomString(FIRST_NAMES), getRandomString(LAST_NAMES), getRandomString(TAX_CODES)));
    }

    private TimeSheetEntryType createTimeSheetEntryType() {
        return timeSheetEntryTypeService.save(new TimeSheetEntryType(getRandomString(RATE_NAMES), new BigDecimal(getRandomString(RATES))));
    }

    public void addFakeData() {
        createEmployee();
        createEmployee();
        createEmployeeAndTimeSheets();
        dataAdded = true;
    }

    public Employee createEmployeeAndTimeSheets() {

        TimeSheetEntryType standard = createTimeSheetEntryType();
        Employee employee1 = createEmployee();
        timeSheetEntryService.save(new TimeSheetEntry(standard, new DateTime().withTimeAtStartOfDay(), new BigDecimal("7.50"), employee1));

        return employee1;
    }

    public void addFakeDataOnce() {
        if (!dataAdded) {
            addFakeData();
        }
    }
}
