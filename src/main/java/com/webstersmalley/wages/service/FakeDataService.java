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

    private final static String[] firstNames = { "Madeleine", "Amelia", "Oscar" };
    private final static String[] lastNames = { "Turnbull", "Gray", "Barlow" };
    private final static String[] taxCodes = { "117L", "028L", "123L" };
    private final static String[] rateNames = {"Standard Day Rate", "Standard Night Rate", "Special Day Rate", "Overtime", "Weekend", "Holiday" };
    private final static String[] rates = { "100.00", "75.00", "10.00", "210.00" };

    private String getRandomString(String[] selection) {
        return selection[(int)(Math.random()*selection.length)];
    }

    private Employee createEmployee() {
        return employeeService.save(new Employee(getRandomString(firstNames), getRandomString(lastNames), getRandomString(taxCodes)));
    }

    private TimeSheetEntryType createTimeSheetEntryType() {
        return timeSheetEntryTypeService.save(new TimeSheetEntryType(getRandomString(rateNames), new BigDecimal(getRandomString(rates))));
    }

    public void addFakeData() {
        createEmployee();
        createEmployee();
        createEmployeeAndTimeSheets();
    }

    public Employee createEmployeeAndTimeSheets() {

        TimeSheetEntryType standard = createTimeSheetEntryType();
        Employee employee1 = createEmployee();
        TimeSheetEntry timeSheetEntry = timeSheetEntryService.save(new TimeSheetEntry(standard, new DateTime().withTimeAtStartOfDay(), new BigDecimal(7.5), employee1));

        return employee1;
    }
}
