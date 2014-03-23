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
import com.webstersmalley.wages.domain.Employee;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by: Matthew Smalley
 * Date: 22/03/14
 */
public class TestEmployeeController extends AbstractSpringAwareBase {

    @Test
    public void testEmployeesPage() throws Exception {
        fakeDataService.addFakeData();
        ModelAndView mav = employeeController.employeesPage();
        assertEquals("employees", mav.getViewName());
        List<Employee> employees = (List<Employee>) mav.getModel().get("employees");
        assertNotNull(employees);
        assertTrue(employees.size() > 0);
    }

    @Test
    public void testEditEmployee() throws Exception {
        Employee employee = employeeService.getAllEmployees().get(0);
        ModelAndView mav = employeeController.editEmployee(employee.getId());
        assertEquals(employee, mav.getModel().get("employee"));
        assertEquals("employeeDetail", mav.getViewName());
    }

    @Test
    public void testSaveEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setFirstNames(getRandomString(15));
        employee.setLastName(getRandomString(10));
        employee.setTaxCode(getRandomString(5));

        List<Employee> employees = employeeRepository.findByLastName(employee.getLastName());
        assertFalse(employees.contains(employee));

        assertEquals("redirect:employees.html", employeeController.saveEmployee(employee, null));
        employees = employeeRepository.findByLastName(employee.getLastName());
        assertTrue(employees.contains(employee));

    }

    @Test
    public void testDeleteEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setFirstNames(getRandomString(15));
        employee.setLastName(getRandomString(10));
        employee.setTaxCode(getRandomString(5));
        employeeController.saveEmployee(employee, null);

        List<Employee> employees = employeeRepository.findByLastName(employee.getLastName());
        assertTrue(employees.contains(employee));

        assertEquals("redirect:employees.html", employeeController.deleteEmployee(employee.getId()));
        employees = employeeRepository.findByLastName(employee.getLastName());
        assertFalse(employees.contains(employee));

    }
}
