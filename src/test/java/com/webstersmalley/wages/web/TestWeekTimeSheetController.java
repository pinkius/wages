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
import com.webstersmalley.wages.domain.WeekTimeSheet;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by: Matthew Smalley
 * Date: 22/03/14
 */
public class TestWeekTimeSheetController extends AbstractSpringAwareBase {
    @Test
    public void testTimeSheet() throws Exception {
        Employee employee = fakeDataService.createEmployeeAndTimeSheets();
        ModelAndView mav = weekTimeSheetController.timeSheet(employee.getId(), null, null);
        assertEquals(mav.getViewName(), "timeSheet");
        List<WeekTimeSheet> weeks = (List<WeekTimeSheet>) mav.getModel().get("weeks");
        assertTrue(weeks.size() > 0);
        for (WeekTimeSheet week : weeks) {
            boolean found = false;
            assertEquals(1, week.getWeekCommencing().getDayOfWeek());
            assertEquals(employee, week.getEmployee());
            found = (found || ((week.getMondayHours() != null) && (week.getMondayHours().compareTo(BigDecimal.ZERO) > 0)));
            found = (found || ((week.getTuesdayHours() != null) && (week.getTuesdayHours().compareTo(BigDecimal.ZERO) > 0)));
            found = (found || ((week.getWednesdayHours() != null) && (week.getWednesdayHours().compareTo(BigDecimal.ZERO) > 0)));
            found = (found || ((week.getThursdayHours() != null) && (week.getThursdayHours().compareTo(BigDecimal.ZERO) > 0)));
            found = (found || ((week.getFridayHours() != null) && (week.getFridayHours().compareTo(BigDecimal.ZERO) > 0)));
            found = (found || ((week.getSaturdayHours() != null) && (week.getSaturdayHours().compareTo(BigDecimal.ZERO) > 0)));
            found = (found || ((week.getSundayHours() != null) && (week.getSundayHours().compareTo(BigDecimal.ZERO) > 0)));
            assertTrue(found);
        }
    }
}
