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

package com.webstersmalley.fees.service;

import com.webstersmalley.fees.AbstractSpringAwareBase;
import com.webstersmalley.fees.domain.PayerType;
import com.webstersmalley.fees.domain.PaymentFrequency;
import com.webstersmalley.fees.domain.PaymentScheduleElement;
import com.webstersmalley.fees.domain.Resident;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by: Matthew Smalley
 * Date: 02/04/14
 */
public class TestPaymentScheduleService extends AbstractSpringAwareBase {


    @Test
    public void testFindByResident() {
        List<PaymentScheduleElement> schedule = paymentScheduleService.findByResident(fakeDataService.getActiveCreditResident());
        assertNotNull(schedule);
        assertEquals(1, schedule.size());
        assertEquals(PayerType.RESIDENT, schedule.get(0).getPayerType());
    }

    @Test
    public void testSave() {
        Resident resident = fakeDataService.createFakeResident();
        List<PaymentScheduleElement> schedule = paymentScheduleService.findByResident(resident);
        assertEquals(0, schedule.size());

        PaymentScheduleElement element = new PaymentScheduleElement(resident, "blah", PayerType.RESIDENT, new BigDecimal("10.00"), PaymentFrequency.MONTHLY, null, null);
        element = paymentScheduleService.save(element);
        schedule = paymentScheduleService.findByResident(resident);
        assertEquals(1, schedule.size());
        assertEquals(element, schedule.get(0));

    }
}
