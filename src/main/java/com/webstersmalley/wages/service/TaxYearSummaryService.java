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

package com.webstersmalley.wages.service;

import com.webstersmalley.wages.domain.Employee;
import com.webstersmalley.wages.domain.TaxWeek;
import com.webstersmalley.wages.domain.WeekTimeSheet;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by: Matthew Smalley
 * Date: 28/03/14
 */
@Service("taxYearSummaryService")
public class TaxYearSummaryService {
    private final static DateTime START_OF_TAX_YEAR = new DateTime("2013-04-01");
    private final static BigDecimal DEFAULT_TAX_RATE = new BigDecimal("0.20");
    @Resource
    private WeekTimeSheetService weekTimeSheetService;

    public List<TaxWeek> getTaxYearSummary(Employee employee) {
        List<WeekTimeSheet> timeSheets = weekTimeSheetService.findByEmployeeAndWeekCommencingBetween(employee.getId(), START_OF_TAX_YEAR, new DateTime());

        Collections.sort(timeSheets, new Comparator<WeekTimeSheet>() {
            @Override
            public int compare(WeekTimeSheet o1, WeekTimeSheet o2) {
                return o1.getWeekCommencing().compareTo(o2.getWeekCommencing());
            }
        });

        List<TaxWeek> taxWeeks = new ArrayList<TaxWeek>();
        BigDecimal cumulativeGross = BigDecimal.ZERO.setScale(2);
        BigDecimal cumulativeTax = BigDecimal.ZERO.setScale(2);

        for (WeekTimeSheet timeSheet : timeSheets) {
            TaxWeek taxWeek = new TaxWeek();

            taxWeek.setEmployee(employee);
            taxWeek.setTaxCode(employee.getTaxCode());
            taxWeek.setWeekCommencing(timeSheet.getWeekCommencing());
            taxWeek.setGrossThisWeek(timeSheet.getTotalGrossWage());
            cumulativeGross = cumulativeGross.add(timeSheet.getTotalGrossWage());
            taxWeek.setCumulativeGross(cumulativeGross);
            BigDecimal totalTaxOutstanding = cumulativeGross.multiply(DEFAULT_TAX_RATE).setScale(cumulativeGross.scale());
            taxWeek.setTaxThisWeek(totalTaxOutstanding.subtract(cumulativeTax));
            taxWeek.setCumulativeTaxToPreviousWeek(cumulativeTax);
            cumulativeTax = totalTaxOutstanding;
            taxWeek.setNetThisWeek(taxWeek.getGrossThisWeek().subtract(taxWeek.getTaxThisWeek()));
            taxWeek.setCumulativeNet(taxWeek.getCumulativeGross().subtract(cumulativeTax));
            taxWeeks.add(taxWeek);
        }

        return taxWeeks;
    }
}
