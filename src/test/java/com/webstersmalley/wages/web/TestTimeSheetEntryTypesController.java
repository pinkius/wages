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
import com.webstersmalley.wages.domain.TimeSheetEntryType;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by: Matthew Smalley
 * Date: 22/03/14
 */
public class TestTimeSheetEntryTypesController extends AbstractSpringAwareBase {
    @Test
    public void testTimeSheetEntryTypes() throws Exception {
        ModelAndView mav = timeSheetEntryTypesController.timeSheetEntryTypes();
        assertEquals(mav.getViewName(), "timeSheetEntryTypes");
        List<TimeSheetEntryType> types = (List<TimeSheetEntryType>) mav.getModel().get("timeSheetEntryTypes");
        assertTrue(types.size() > 0);
    }

    @Test
    public void testSaveTimeSheetEntryType() throws Exception {
        List<TimeSheetEntryType> types = timeSheetEntryTypeRepository.findAll();
        TimeSheetEntryType type = new TimeSheetEntryType();
        type.setName(getRandomString(15));
        type.setHourlyRate(new BigDecimal("10.5"));
        assertFalse(types.contains(type));
        assertEquals("redirect:timeSheetEntryTypes.html", timeSheetEntryTypesController.saveTimeSheetEntryType(type, null));
        types = timeSheetEntryTypeRepository.findAll();
        assertTrue(types.contains(type));
    }

    @Test
    public void testDeleteTimeSheetEntryType() throws Exception {
        TimeSheetEntryType type = new TimeSheetEntryType();
        type.setName(getRandomString(15));
        type.setHourlyRate(new BigDecimal("10.5"));
        assertEquals("redirect:timeSheetEntryTypes.html", timeSheetEntryTypesController.saveTimeSheetEntryType(type, null));
        List<TimeSheetEntryType> types = timeSheetEntryTypeRepository.findAll();
        assertTrue(types.contains(type));
        for (TimeSheetEntryType listedType : types) {
            if (listedType.equals(type)) {
                type = listedType;
                break;
            }
        }
        timeSheetEntryTypesController.deleteTimeSheetEntryType(type.getId());
        types = timeSheetEntryTypeRepository.findAll();
        assertFalse(types.contains(type));
    }
}
