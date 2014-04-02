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

import de.svenjacobs.loremipsum.LoremIpsum;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by: Matthew Smalley
 * Date: 01/04/14
 */
@Service
public class DataGenerationService {
    private static final List<String> FIRST_NAMES = new ArrayList<>();
    private static final List<String> LAST_NAMES = new ArrayList<>();
    private static final List<String> CHARGE_TYPES = new ArrayList<>();
    public static final LocalDate TODAY = new LocalDate();

    private Set<String> usedNames = new HashSet<>();

    private Random r = new Random();
    private LoremIpsum loremIpsum = new LoremIpsum();
    private int commentCounter = 0;

    private static void loadEntries(String resource, List<String> list) throws IOException {
        InputStream is = null;
        try {
            is = DataGenerationService.class.getResourceAsStream(resource);
            list.addAll(IOUtils.readLines(is));
        } finally {
            IOUtils.closeQuietly(is);
        }
    }

    static {
        try {
            loadEntries("/firstnames.txt", FIRST_NAMES);
            loadEntries("/lastnames.txt", LAST_NAMES);
            loadEntries("/chargetypes.txt", CHARGE_TYPES);
        } catch (IOException e) {
            throw new RuntimeException("Error loading sample data", e);
        }
    }

    /**
     * Creates a random telephone number
     */
    public String generateTelephoneNumber() {
        StringBuilder telephoneNumber = new StringBuilder();
        telephoneNumber.append("0");
        telephoneNumber.append(RandomStringUtils.randomNumeric(4));
        telephoneNumber.append(" ");
        telephoneNumber.append(RandomStringUtils.randomNumeric(6));
        return telephoneNumber.toString();
    }

    /**
     * Creates a random NI number
     */
    public String generateNINumber() {
        StringBuilder niNumber = new StringBuilder();
        niNumber.append(RandomStringUtils.randomAlphabetic(2).toUpperCase());
        niNumber.append(RandomStringUtils.randomNumeric(6));
        niNumber.append(RandomStringUtils.randomAlphabetic(1).toUpperCase());
        return niNumber.toString();
    }


    /**
     * Creates a random / unique name
     */
    public String generateName() {
        for (int i = 0; i < 100; i++) {
            String name = generateRandomElementFromList(FIRST_NAMES) + " " + generateRandomElementFromList(LAST_NAMES);
            if (!usedNames.contains(name)) {
                usedNames.add(name);
                return name;
            }
        }
        throw new RuntimeException("Couldn't create a unique name - consider adding more names to the seed lists.");
    }

    private char getUpperCaseAlpha() {
        return (char) (r.nextInt(26) + 'A');
    }

    /**
     * Generates a room number (range 0-1000)
     *
     * @return
     */
    public String generateRoomNumber() {
        return new Integer((int) (Math.random() * 1000)).toString();
    }

    public LocalDate generateDateOfArrival() {
        return TODAY.minusDays((int) (Math.random() * 10 * 365));
    }

    public LocalDate generateDateOfBirth() {
        return TODAY.minusYears(50).minusDays((int) (Math.random() * 70 * 365));
    }

    public BigDecimal generateBigDecimal(BigDecimal max) {
        return new BigDecimal(r.nextDouble()).multiply(max).setScale(max.scale(), RoundingMode.HALF_UP);
    }

    public BigDecimal generateRoomRate() {
        return new BigDecimal("20.00");
    }

    public String generateChargeType() {
        return generateRandomElementFromList(CHARGE_TYPES);
    }

    public String generateComment() {
        String returnString = loremIpsum.getWords(50, commentCounter) + ".";
        commentCounter = commentCounter + 5 % 50;
        return returnString;
    }

    public <T> T generateRandomElementFromList(List<T> list) {
        return list.get((int) (Math.random() * list.size()));
    }
}
