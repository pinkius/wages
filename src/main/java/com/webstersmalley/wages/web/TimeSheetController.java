package com.webstersmalley.wages.web;

import com.webstersmalley.wages.domain.TimeSheetEntryType;
import com.webstersmalley.wages.service.TimeSheetEntryService;
import com.webstersmalley.wages.service.TimeSheetEntryTypeService;
import org.joda.time.DateTime;
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
public class TimeSheetController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private TimeSheetEntryService timeSheetEntryService;

    @RequestMapping(value = "/timeSheet")
    public ModelAndView timeSheet(@RequestParam Long employeeId, @RequestParam(required = false) DateTime start, @RequestParam(required = false) DateTime end) {
        ModelAndView mav = new ModelAndView("timeSheet");
        mav.addObject("timeSheetEntryTypes", timeSheetEntryService.findByEmployeeAndDateBetween(employeeId, start, end));
        return mav;
    }

}
