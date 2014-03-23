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

import com.webstersmalley.wages.domain.WeekTimeSheet;
import com.webstersmalley.wages.service.EmployeeService;
import com.webstersmalley.wages.service.WeekTimeSheetService;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
@Controller
public class WeekTimeSheetController {
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private WeekTimeSheetService weekTimeSheetService;

    @Resource
    private EmployeeService employeeService;

    @RequestMapping(value = "/timeSheet")
    public ModelAndView timeSheet(@RequestParam Long employeeId, @RequestParam(required = false) DateTime start, @RequestParam(required = false) DateTime end) {
        ModelAndView mav = new ModelAndView("timeSheet");
        mav.addObject("weeks", weekTimeSheetService.findByEmployeeAndWeekCommencingBetween(employeeId, start, end));
        return mav;
    }

    @RequestMapping(value = "/saveTimeSheet")
    public String saveTimeSheet(@RequestParam Long weekTimeSheetId, @RequestParam Long employeeId, @RequestParam String weekCommencing, @RequestParam BigDecimal mondayHours, @RequestParam BigDecimal tuesdayHours, @RequestParam BigDecimal wednesdayHours, @RequestParam BigDecimal thursdayHours, @RequestParam BigDecimal fridayHours, @RequestParam BigDecimal saturdayHours, @RequestParam BigDecimal sundayHours) {
        WeekTimeSheet week = new WeekTimeSheet();
        week.setId(weekTimeSheetId);
        week.setEmployee(employeeService.findById(employeeId));
        week.setWeekCommencing(formatter.parseDateTime(weekCommencing));
        week.setMondayHours(mondayHours);
        week.setTuesdayHours(tuesdayHours);
        week.setWednesdayHours(wednesdayHours);
        week.setThursdayHours(thursdayHours);
        week.setFridayHours(fridayHours);
        week.setSaturdayHours(saturdayHours);
        week.setSundayHours(sundayHours);
        logger.info("Found this: {}", week);

        weekTimeSheetService.saveTimeSheet(week);
        return "redirect:timeSheet.html?employeeId=" + week.getEmployee().getId();
    }
}
