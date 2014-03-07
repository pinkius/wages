package com.webstersmalley.wages.repository;

import com.webstersmalley.wages.domain.Employee;
import com.webstersmalley.wages.domain.TimeSheetEntry;
import org.joda.time.DateTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by: Matthew Smalley
 * Date: 06/03/14
 */
@Transactional(readOnly=true)
public interface TimeSheetEntryRepository extends CrudRepository<TimeSheetEntry, Long> {
    List<TimeSheetEntry> findAll();
    List<TimeSheetEntry> findAllByEmployeeId(Long employeeId);

    @Transactional(readOnly=false)
    TimeSheetEntry save(TimeSheetEntry timeSheetEntry);

    TimeSheetEntry findById(Long id);

    List<TimeSheetEntry> findByEmployeeAndDateBetween(Employee employee, DateTime startDate, DateTime endDate);
}
