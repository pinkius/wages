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

package com.webstersmalley.fees;

import com.webstersmalley.fees.repository.ResidentRepository;
import com.webstersmalley.fees.repository.RoomRepository;
import com.webstersmalley.fees.service.DataGenerationService;
import com.webstersmalley.fees.service.FakeDataService;
import com.webstersmalley.fees.service.PaymentScheduleService;
import com.webstersmalley.fees.service.ResidentAccountService;
import com.webstersmalley.fees.service.ResidentService;
import com.webstersmalley.fees.service.RoomService;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import javax.annotation.Resource;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
@ContextConfiguration("/fees.xml")
public class AbstractSpringAwareBase extends AbstractJUnit4SpringContextTests {

    @Resource
    protected DataGenerationService dataGenerationService;

    @Resource
    protected RoomRepository roomRepository;

    @Resource
    protected RoomService roomService;

    @Resource
    protected ResidentRepository residentRepository;

    @Resource
    protected ResidentService residentService;

    @Resource
    protected ResidentAccountService residentAccountService;

    @Resource
    protected FakeDataService fakeDataService;

    @Resource
    protected PaymentScheduleService paymentScheduleService;

    @BeforeClass
    public static void setup() {
        System.setProperty("environment", "dev");
    }

    @Before
    public void setupData() {
        fakeDataService.createFakeDataOnce();
    }

    @Test
    public void testSpringContextLoads() {

    }

}
