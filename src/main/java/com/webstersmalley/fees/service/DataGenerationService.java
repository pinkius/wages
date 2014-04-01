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
import org.apache.commons.lang3.RandomStringUtils;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by: Matthew Smalley
 * Date: 01/04/14
 */
@Service
public class DataGenerationService {
    private static final String[] FIRST_NAMES = {"Abram",
            "Allena",
            "Amalia",
            "Angie",
            "Arnetta",
            "Azucena",
            "Bill",
            "Billy",
            "Casimira",
            "Chong",
            "Cindi",
            "Clark",
            "Claudette",
            "Cruz",
            "Daron",
            "Daysi",
            "Debera",
            "Delcie",
            "Denisha",
            "Derek",
            "Dominga",
            "Elayne",
            "Elisabeth",
            "Emely",
            "Emerald",
            "Emmaline",
            "Ernestina",
            "Fredda",
            "Genny",
            "Gianna",
            "Ginger",
            "Goldie",
            "Hien",
            "Jada",
            "Janee",
            "Jarred",
            "Jerome",
            "Jessica",
            "Jina",
            "Julia",
            "Latesha",
            "Liane",
            "Loreta",
            "Lynelle",
            "Mable",
            "Malcolm",
            "Malinda",
            "Merrill",
            "Min",
            "Pamala",
            "Pearle",
            "Phebe",
            "Piedad",
            "Rashad",
            "Rick",
            "Ronny",
            "Rosalinda",
            "Rossana",
            "Scarlet",
            "Shonda",
            "Sophia",
            "Spring",
            "Stacey",
            "Sydney",
            "Terese",
            "Tiesha",
            "Valarie",
            "Wes",
            "Yajaira",
            "Zada"};
    private static final String[] LAST_NAMES = {"Agarwal", "Bealer", "Bicknell", "Boulton", "Breault", "Brenes", "Buff", "Caesar", "Capps", "Casperson", "Cossey", "Cranfill", "Curtis", "Darcy", "Drake", "Duran", "Durrance", "Edling", "Efird", "Esslinger", "Fleet", "Gilbreath", "Goldston", "Hallock", "Harford", "Hendrie", "Hillyer", "Hinderliter", "Hodgson", "Holderman", "Hopp", "Horsman", "Jerabek", "Knoblock", "Lamontagne", "Lebaron", "Madry", "Marquart", "Marshburn", "Mauger", "Mccallister", "Moffatt", "Morejon", "Murdock", "Nakasone", "Novick", "Oesterling", "Peachey", "Pike", "Pineau", "Polson", "Ricardo", "Ruffino", "Saliba", "Schwing", "Sides", "Sidle", "Simoneau", "Squier", "Streeter", "Tores", "Usher", "Vidales", "Walczak", "Weisser", "Wilker", "Wimbish", "Wingate", "Woodson", "You"};
    private static final String[] CHARGE_TYPES = {"Hairdressing", "Escort", "Chiropodist"};
    public static final LocalDate TODAY = new LocalDate();

    private Set<String> usedNames = new HashSet<>();

    private Random r = new Random();
    private LoremIpsum loremIpsum = new LoremIpsum();
    private int commentCounter = 0;


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
            String name = getRandomString(FIRST_NAMES) + " " + getRandomString(LAST_NAMES);
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

    public BigDecimal generateRoomRate() {
        return new BigDecimal("20.00");
    }

    public String generateChargeType() {
        return getRandomString(CHARGE_TYPES);
    }

    private String getRandomString(String[] selection) {
        return selection[(int) (Math.random() * selection.length)];
    }


    public String generateComment() {
        String returnString = loremIpsum.getWords(50, commentCounter) + ".";
        commentCounter = commentCounter + 5 % 50;
        return returnString;
    }
}
