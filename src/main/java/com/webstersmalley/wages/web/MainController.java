package com.webstersmalley.wages.web;

import com.webstersmalley.wages.service.EmployeeService;
import com.webstersmalley.wages.service.FakeDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
@Controller("mainController")
public class MainController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private FakeDataService fakeDataService;

    @RequestMapping(value = "/wages")
    public ModelAndView wagesMainPage() {
        ModelAndView mav = new ModelAndView("mainpage");
        return mav;
    }

    @RequestMapping(value = "/addFakeData")
    public ModelAndView addFakeData() {
        fakeDataService.addFakeData();
        ModelAndView mav = new ModelAndView("mainpage");
        return mav;
    }
}
