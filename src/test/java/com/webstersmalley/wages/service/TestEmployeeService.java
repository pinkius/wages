package com.webstersmalley.wages.service;

import com.webstersmalley.wages.AbstractSpringAwareBase;
import com.webstersmalley.wages.domain.Employee;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
public class TestEmployeeService extends AbstractSpringAwareBase {


    @Test
    public void testSaveAndLoad() {
        List<Employee> initialList = employeeService.getAllEmployees();

        Employee employee = new Employee("Joe", "Bloggs", "150L");
        Employee employee2 = employeeService.save(employee);

        assertEquals(employee, employee2);
        assertNotNull(employee.getId());

        List<Employee> postSaveList = employeeService.getAllEmployees();

        assertTrue(postSaveList.size() > initialList.size());

    }
}
