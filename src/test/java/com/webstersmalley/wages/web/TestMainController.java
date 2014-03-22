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
