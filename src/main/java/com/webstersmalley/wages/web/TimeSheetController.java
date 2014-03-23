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

import com.webstersmalley.wages.service.TimeSheetEntryService;
import org.joda.time.DateTime;
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
public class TimeSheetController {
    @Resource
    private TimeSheetEntryService timeSheetEntryService;

    @RequestMapping(value = "/timeSheet")
    public ModelAndView timeSheet(@RequestParam Long employeeId, @RequestParam(required = false) DateTime start, @RequestParam(required = false) DateTime end) {
        ModelAndView mav = new ModelAndView("timeSheet");
        mav.addObject("timeSheetRows", timeSheetEntryService.getTimeSheetByEmployeeAndDateRange(employeeId, start, end));
        return mav;
    }

    @RequestMapping(value = "/saveTimeSheet")
    public String saveTimeSheet(Long employeeId, String weekCommencing, BigDecimal hours0, BigDecimal hours1, BigDecimal hours2, BigDecimal hours3, BigDecimal hours4, BigDecimal hours5, BigDecimal hours6) {
        timeSheetEntryService.saveTimeSheet(employeeId, weekCommencing, hours0, hours1, hours2, hours3, hours4, hours5, hours6);
        return "redirect:timeSheet.html?employeeId=" + employeeId;
    }
}
