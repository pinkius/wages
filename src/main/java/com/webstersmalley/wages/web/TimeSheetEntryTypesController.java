package com.webstersmalley.wages.web;

import com.webstersmalley.wages.domain.Employee;
import com.webstersmalley.wages.domain.TimeSheetEntryType;
import com.webstersmalley.wages.service.EmployeeService;
import com.webstersmalley.wages.service.TimeSheetEntryService;
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
import java.util.List;

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
