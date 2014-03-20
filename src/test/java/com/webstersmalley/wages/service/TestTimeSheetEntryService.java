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
