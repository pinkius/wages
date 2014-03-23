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
