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
        return employeeRepository.findAll();
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
