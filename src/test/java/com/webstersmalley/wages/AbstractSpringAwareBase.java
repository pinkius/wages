package com.webstersmalley.wages;

import com.webstersmalley.wages.repository.EmployeeRepository;
import com.webstersmalley.wages.repository.TimeSheetEntryTypeRepository;
import com.webstersmalley.wages.service.EmployeeService;
import com.webstersmalley.wages.service.FakeDataService;
import com.webstersmalley.wages.service.TimeSheetEntryService;
import com.webstersmalley.wages.service.TimeSheetEntryTypeService;
import com.webstersmalley.wages.web.EmployeeController;
import com.webstersmalley.wages.web.MainController;
import com.webstersmalley.wages.web.TimeSheetController;
import com.webstersmalley.wages.web.TimeSheetEntryTypesController;
import org.junit.Before;
import org.junit.BeforeClass;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
@ContextConfiguration("/wages.xml")
public class AbstractSpringAwareBase extends AbstractJUnit4SpringContextTests {
    private SecureRandom random = new SecureRandom();

    public String getRandomString(int length) {
        return new BigInteger(length, random).toString(32);
    }

    @Resource
    protected EmployeeRepository employeeRepository;

    @Resource
    protected TimeSheetEntryTypeRepository timeSheetEntryTypeRepository;

    @Resource
    protected EmployeeService employeeService;

    @Resource
    protected FakeDataService fakeDataService;

    @Resource
    protected TimeSheetEntryService timeSheetEntryService;

    @Resource
    protected TimeSheetEntryTypeService timeSheetEntryTypeService;

    @Resource
    protected MainController mainController;

    @Resource
    protected EmployeeController employeeController;

    @Resource
    protected TimeSheetController timeSheetController;

    @Resource
    protected TimeSheetEntryTypesController timeSheetEntryTypesController;

    @BeforeClass
    public static void setup() {
        System.setProperty("environment", "dev");
    }


    @Before
    public void setupData() {
        fakeDataService.addFakeDataOnce();
    }

}
