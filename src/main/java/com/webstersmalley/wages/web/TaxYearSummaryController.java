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

import com.webstersmalley.wages.domain.Employee;
import com.webstersmalley.wages.service.EmployeeService;
import com.webstersmalley.wages.service.TaxYearSummaryService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by: Matthew Smalley
 * Date: 28/03/14
 */
@Controller
public class TaxYearSummaryController {
    @Resource
    private EmployeeService employeeService;
    @Resource
    private TaxYearSummaryService taxYearSummaryService;

    @RequestMapping(value = "/taxYearSummary")
    public ModelAndView timeSheet(@RequestParam Long employeeId, @RequestParam(required = false) DateTime start, @RequestParam(required = false) DateTime end) {
        Employee employee = employeeService.findById(employeeId);
        ModelAndView mav = new ModelAndView("taxYearSummary");
        mav.addObject("weeks", taxYearSummaryService.getTaxYearSummary(employee));
        return mav;
    }


}
