package com.webstersmalley.fees.service;/*************************************************************************
 Copyright 2011 Webstersmalley

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 *************************************************************************/

import com.webstersmalley.fees.AbstractSpringAwareBase;
import com.webstersmalley.fees.domain.Resident;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created: 30/03/2014
 */
public class TestResidentService extends AbstractSpringAwareBase {
    @Test
    public void testSaveResident() {
        int initialSize = residentService.findAll().size();
        Resident resident = fakeDataService.createFakeResident();
        List<Resident> residentList = residentService.findAll();
        assertEquals(initialSize + 1, residentList.size());
        assertTrue(residentList.contains(resident));
        Resident resident1 = residentService.findById(resident.getId());
        assertNotNull(resident1.getName());
        assertNotNull(resident1.getDateOfBirth());

        System.out.println(resident1);
    }
}
