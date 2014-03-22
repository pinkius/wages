package com.webstersmalley.wages.web;

import com.webstersmalley.wages.AbstractSpringAwareBase;
import com.webstersmalley.wages.domain.Employee;
import com.webstersmalley.wages.domain.TimeSheetEntry;
import com.webstersmalley.wages.domain.TimeSheetRow;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by: Matthew Smalley
 * Date: 22/03/14
 */
public class TestTimeSheetController extends AbstractSpringAwareBase {
    @Test
    public void testTimeSheet() throws Exception {
        Employee employee = fakeDataService.createEmployeeAndTimeSheets();
        ModelAndView mav = timeSheetController.timeSheet(employee.getId(), null, null);
        assertEquals(mav.getViewName(), "timeSheet");
        List<TimeSheetRow> rows = (List<TimeSheetRow>) mav.getModel().get("timeSheetRows");
        assertTrue(rows.size() > 0);
        for (TimeSheetRow row: rows) {
            boolean found = false;
            assertEquals(1, row.getWeekCommencing().getDayOfWeek());
            for (TimeSheetEntry entry: row.getEntries()) {
                if (entry != null) {
                    assertEquals(employee, entry.getEmployee());
                    assertNotNull(entry.getHours());
                    assertNotNull(entry.getTimeSheetEntryType());
                    assertFalse(entry.getDate().isBefore(row.getWeekCommencing()));
                    assertTrue(entry.getDate().isBefore(row.getWeekCommencing().plusWeeks(1)));
                    found = true;
                }
            }
            assertTrue(found);
        }
    }
}
