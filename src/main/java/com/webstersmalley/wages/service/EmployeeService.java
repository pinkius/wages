package com.webstersmalley.wages.service;

import com.webstersmalley.wages.domain.Employee;
import com.webstersmalley.wages.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
@Service("employeeService")
public class EmployeeService {
    @Resource
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        if (employeeRepository.findAll().size() == 0) {
            addTestData();
        }
        return employeeRepository.findAll();
    }

    private void addTestData() {
        Employee employee = new Employee();
        employee.setFirstNames("Joe");
        employee.setLastName("Bloggsie");
        save(employee);
    }

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id);
    }

    public void delete(Long id) {
        employeeRepository.delete(employeeRepository.findById(id));
    }
}
