package com.webstersmalley.wages;

import com.webstersmalley.wages.service.EmployeeService;
import com.webstersmalley.wages.service.FakeDataService;
import com.webstersmalley.wages.service.TimeSheetEntryService;
import com.webstersmalley.wages.service.TimeSheetEntryTypeService;
import org.junit.BeforeClass;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
@ContextConfiguration("/wages.xml")
public class AbstractSpringAwareBase extends AbstractJUnit4SpringContextTests {
    @Resource
    protected EmployeeService employeeService;

    @Resource
    protected FakeDataService fakeDataService;

    @Resource
    protected TimeSheetEntryService timeSheetEntryService;

    @Resource
    protected TimeSheetEntryTypeService timeSheetEntryTypeService;

    @BeforeClass
    public static void setup() {
        System.setProperty("environment", "dev");
    }

}
