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
import com.webstersmalley.fees.domain.ResidentAccount;
import com.webstersmalley.fees.service.ResidentAccountService;
import com.webstersmalley.fees.service.ResidentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created: 30/03/2014
 */
@Controller
public class ResidentAccountController {
    @Resource
    private ResidentService residentService;
    @Resource
    private ResidentAccountService residentAccountService;

    @RequestMapping("/residentAccount")
    public ModelAndView getResidentAccount(Long residentId) {
        Resident resident = residentService.findById(residentId);
        ResidentAccount account = residentAccountService.getAccountForResident(resident);
        ModelAndView model = new ModelAndView("residentAccount");
        model.addObject("account", account);
        return model;
    }

    @RequestMapping("/residentAccountSummary")
    public ModelAndView getResidentAccountSummary() {
        ModelAndView model = new ModelAndView("residentAccountSummary");
        model.addObject("accountSummaries", residentAccountService.getAllResidentAccountSummaries());
        return model;
    }
}
