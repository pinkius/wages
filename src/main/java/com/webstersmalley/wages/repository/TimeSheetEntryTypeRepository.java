package com.webstersmalley.wages.repository;

import com.webstersmalley.wages.domain.TimeSheetEntry;
import com.webstersmalley.wages.domain.TimeSheetEntryType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by: Matthew Smalley
 * Date: 06/03/14
 */
@Transactional(readOnly=true)
public interface TimeSheetEntryTypeRepository extends CrudRepository<TimeSheetEntryType, Long> {
    List<TimeSheetEntryType> findAll();

    @Transactional(readOnly=false)
    TimeSheetEntryType save(TimeSheetEntryType timeSheetEntryType);

    TimeSheetEntryType findById(Long id);

    @Transactional(readOnly=false)
    void delete(TimeSheetEntryType timeSheetEntryType);
}
