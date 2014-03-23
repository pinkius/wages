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

import com.webstersmalley.wages.AbstractSpringAwareBase;
import com.webstersmalley.wages.domain.Employee;
import com.webstersmalley.wages.domain.TimeSheetRow;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
public class TestTimeSheetEntryService extends AbstractSpringAwareBase {

    @Test
    public void testDateRange() {
        Employee employee = fakeDataService.createEmployeeAndTimeSheets();
        Assert.assertTrue(timeSheetEntryService.getTimeSheetByEmployeeAndDateRange(employee.getId(), null, null).size() > 0);
        Assert.assertTrue(timeSheetEntryService.getTimeSheetByEmployeeAndDateRange(employee.getId(), new DateTime().minusDays(365), null).size() == 0);
        Assert.assertTrue(timeSheetEntryService.getTimeSheetByEmployeeAndDateRange(employee.getId(), null, new DateTime().minusDays(365)).size() == 0);
        Assert.assertTrue(timeSheetEntryService.getTimeSheetByEmployeeAndDateRange(employee.getId(), new DateTime().minusDays(365), new DateTime().plusDays(10)).size() > 0);
    }

    @Test
    public void testTimeSheetRows() {
        Employee employee = fakeDataService.createEmployeeAndTimeSheets();
        List<TimeSheetRow> rows = timeSheetEntryService.getTimeSheetByEmployeeAndDateRange(employee.getId(), null, null);
        Assert.assertTrue(rows.size() > 0);



    }

}
