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

package com.webstersmalley.wages.web;

import com.webstersmalley.wages.AbstractSpringAwareBase;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by: Matthew Smalley
 * Date: 22/03/14
 */
public class TestMainController extends AbstractSpringAwareBase {
    @Test
    public void testMainController() {
        assertEquals("mainpage", mainController.wagesMainPage());
    }

    @Test
    public void testAddFakeData() {
        int origEmployeeCount = employeeService.getAllEmployees().size();
        assertEquals("mainpage", mainController.addFakeData());
        assertTrue(employeeService.getAllEmployees().size() > origEmployeeCount);
    }

}
