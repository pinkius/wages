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

import com.webstersmalley.wages.domain.TimeSheetEntryType;
import com.webstersmalley.wages.service.TimeSheetEntryTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
@Controller
public class TimeSheetEntryTypesController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TimeSheetEntryTypeService timeSheetEntryTypeService;

    @RequestMapping(value = "/timeSheetEntryTypes")
    public ModelAndView timeSheetEntryTypes() {
        ModelAndView mav = new ModelAndView("timeSheetEntryTypes");
        mav.addObject("timeSheetEntryTypes", timeSheetEntryTypeService.findAll());
        return mav;
    }

    @RequestMapping(value = "/saveTimeSheetEntryType")
    public String saveTimeSheetEntryType(@ModelAttribute("command")TimeSheetEntryType timeSheetEntryType,
                               BindingResult result){
        logger.info("Saving timeSheetEntryType: {}", timeSheetEntryType);
        timeSheetEntryTypeService.save(timeSheetEntryType);
        return "redirect:timeSheetEntryTypes.html";
    }

    @RequestMapping(value = "/deleteTimeSheetEntryType")
    public String deleteTimeSheetEntryType(@RequestParam Long id) {
        timeSheetEntryTypeService.deleteById(id);
        return "redirect:timeSheetEntryTypes.html";
    }
}
