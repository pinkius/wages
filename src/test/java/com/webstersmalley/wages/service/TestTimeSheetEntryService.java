package com.webstersmalley.wages.service;

import com.webstersmalley.wages.AbstractSpringAwareBase;
import com.webstersmalley.wages.domain.Employee;
import com.webstersmalley.wages.domain.TimeSheetEntry;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
public class TestTimeSheetEntryService extends AbstractSpringAwareBase {

    @Test
    public void testDateRange() {
        Employee employee = fakeDataService.createEmployeeAndTimeSheets();
        Assert.assertTrue(timeSheetEntryService.findByEmployeeAndDateBetween(employee.getId(), null, null).size() > 0);
        Assert.assertTrue(timeSheetEntryService.findByEmployeeAndDateBetween(employee.getId(), new DateTime().minusDays(365), null).size() == 0);
        Assert.assertTrue(timeSheetEntryService.findByEmployeeAndDateBetween(employee.getId(), null, new DateTime().minusDays(365)).size() == 0);
        Assert.assertTrue(timeSheetEntryService.findByEmployeeAndDateBetween(employee.getId(), new DateTime().minusDays(365), new DateTime().plusDays(10)).size() > 0);
    }

}
