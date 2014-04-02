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

package com.webstersmalley.fees.service;import com.webstersmalley.fees.AbstractSpringAwareBase;
import com.webstersmalley.fees.domain.Resident;
import com.webstersmalley.fees.domain.ResidentAccount;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created: 30/03/2014
 */
public class TestResidentAccountService extends AbstractSpringAwareBase {
    @Test
    public void testGetResidentAccount() {
        Resident resident = fakeDataService.createFakeResidentWithRoomAndCharges();

        ResidentAccount account = residentAccountService.getAccountForResident(resident);
        Assert.assertTrue(account.getTransactions().size() > 1);
    }

    @Test
    public void testGetAllResidentAccountSummaries() {
        List<Resident> residents = residentService.findAll();
        List<ResidentAccount> accounts = residentAccountService.getAllResidentAccountSummaries();
        assertEquals(residents.size(), accounts.size());
        for (Resident resident : residents) {
            boolean found = false;
            for (ResidentAccount residentAccount : accounts) {
                if (residentAccount.getResident().equals(resident)) {
                    found = true;
                    break;
                }
            }
            assertTrue(found);
        }
    }
}
