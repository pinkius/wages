package com.webstersmalley.wages.repository;

import com.webstersmalley.wages.domain.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by: Matthew Smalley
 * Date: 06/03/14
 */
@Transactional(readOnly=true)
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    List<Employee> findByLastName(String lastName);
    List<Employee> findAll();

    @Transactional(readOnly=false)
    Employee save(Employee employee);

    Employee findById(Long id);

    @Transactional(readOnly=false)
    void delete(Employee employee);
}
