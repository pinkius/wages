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

package com.webstersmalley.wages;

import com.webstersmalley.wages.repository.EmployeeRepository;
import com.webstersmalley.wages.repository.TimeSheetEntryTypeRepository;
import com.webstersmalley.wages.service.EmployeeService;
import com.webstersmalley.wages.service.FakeDataService;
import com.webstersmalley.wages.service.TaxYearSummaryService;
import com.webstersmalley.wages.service.TimeSheetEntryTypeService;
import com.webstersmalley.wages.service.WeekTimeSheetService;
import com.webstersmalley.wages.web.EmployeeController;
import com.webstersmalley.wages.web.MainController;
import com.webstersmalley.wages.web.TimeSheetEntryTypesController;
import com.webstersmalley.wages.web.WeekTimeSheetController;
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
    protected WeekTimeSheetService weekTimeSheetService;

    @Resource
    protected TimeSheetEntryTypeService timeSheetEntryTypeService;

    @Resource
    protected MainController mainController;

    @Resource
    protected EmployeeController employeeController;

    @Resource
    protected WeekTimeSheetController weekTimeSheetController;

    @Resource
    protected TimeSheetEntryTypesController timeSheetEntryTypesController;

    @Resource
    protected TaxYearSummaryService taxYearSummaryService;

    @BeforeClass
    public static void setup() {
        System.setProperty("environment", "dev");
    }


    @Before
    public void setupData() {
        fakeDataService.addFakeDataOnce();
    }

}
