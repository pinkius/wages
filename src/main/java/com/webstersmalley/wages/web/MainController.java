package com.webstersmalley.wages.web;

import com.webstersmalley.wages.service.FakeDataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
@Controller("mainController")
public class MainController {
    @Resource
    private FakeDataService fakeDataService;

    @RequestMapping(value = "/wages")
    public String wagesMainPage() {
        return "mainpage";
    }

    @RequestMapping(value = "/addFakeData")
    public String addFakeData() {
        fakeDataService.addFakeData();
        return "mainpage";
    }
}
