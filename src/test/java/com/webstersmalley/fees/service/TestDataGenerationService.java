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

import org.joda.time.LocalDate;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * Created by: Matthew Smalley
 * Date: 01/04/14
 */
public class TestDataGenerationService {
    private DataGenerationService dataGenerationService = new DataGenerationService();

    private void testGeneratedString(String generatedString, String pattern) {
        assertNotNull(generatedString);
        assertTrue(generatedString.matches(pattern));
    }

    @Test
    public void testGenerateTelephoneNumber() throws Exception {
        testGeneratedString(dataGenerationService.generateTelephoneNumber(), "[0-9]* [0-9]*");
    }

    @Test
    public void testGenerateNINumber() throws Exception {
        testGeneratedString(dataGenerationService.generateNINumber(), "[A-Z][A-Z][0-9][0-9][0-9][0-9][0-9][0-9][A-Z]");
    }

    @Test
    public void testGenerateName() throws Exception {
        testGeneratedString(dataGenerationService.generateName(), "[A-Z][a-z]* [A-Z][a-z]*");
        DataGenerationService dgs1 = new DataGenerationService();
        Set<String> names = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            String name = dgs1.generateName();
            assertFalse(names.contains(name));
            names.add(name);
        }
    }

    @Test
    public void testGenerateRoomNumber() throws Exception {
        testGeneratedString(dataGenerationService.generateRoomNumber(), "[0-9]*");
    }

    @Test
    public void testGenerateDateOfArrival() throws Exception {
        LocalDate date = dataGenerationService.generateDateOfArrival();
        assertNotNull(date);
        assertTrue(date.isBefore(new LocalDate()));
    }

    @Test
    public void testGenerateDateOfBirth() throws Exception {
        LocalDate date = dataGenerationService.generateDateOfBirth();
        assertNotNull(date);
        assertTrue(date.isBefore(new LocalDate()));
    }

    @Test
    public void testGenerateRoomRate() throws Exception {
        BigDecimal rate = dataGenerationService.generateRoomRate();
        assertNotNull(rate);
        assertTrue(rate.compareTo(BigDecimal.ZERO) > 0);
        assertEquals(2, rate.scale());
    }

    @Test
    public void testGenerateChargeType() throws Exception {
        testGeneratedString(dataGenerationService.generateChargeType(), "[A-z]*");
    }

    @Test
    public void testGenerateComment() throws Exception {
        testGeneratedString(dataGenerationService.generateComment(), "[A-z ,.]*\\.");
    }
}
