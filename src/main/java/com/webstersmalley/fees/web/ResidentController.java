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

package com.webstersmalley.fees.web;import com.webstersmalley.fees.domain.Resident;
import com.webstersmalley.fees.service.ResidentService;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created: 30/03/2014
 */
@Controller
public class ResidentController {
    private DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private ResidentService residentService;

    @RequestMapping(value = "/residents")
    public ModelAndView employeesPage() {
        ModelAndView mav = new ModelAndView("residents");
        List<Resident> residents = residentService.findAll();
        logger.info("Returning: {} residents", residents.size());
        mav.addObject("residents", residents);
        return mav;
    }


    @RequestMapping(value = "/saveResident")
    public String saveEmployee(@RequestParam Long id, @RequestParam String name, @RequestParam String dateOfBirth, @RequestParam boolean active, @RequestParam String dateOfArrival, @RequestParam String contactName, @RequestParam String contactTelephone, @RequestParam String niNumber, @RequestParam String comment) {
        Resident resident = new Resident(name, formatter.parseLocalDate(dateOfBirth), active, formatter.parseLocalDate(dateOfArrival), contactName, contactTelephone, niNumber, comment);
        resident.setId(id);

        logger.info("Saving resident: {}", resident);
        residentService.save(resident);
        return "redirect:residents.html";
    }

    @RequestMapping(value = "/deleteResident")
    public String deleteEmployee(@RequestParam Long id) {
        residentService.delete(id);
        return "redirect:residents.html";
    }
}
