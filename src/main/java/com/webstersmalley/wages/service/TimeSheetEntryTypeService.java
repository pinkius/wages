package com.webstersmalley.wages.service;

import com.webstersmalley.wages.domain.TimeSheetEntryType;
import com.webstersmalley.wages.repository.TimeSheetEntryTypeRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by: Matthew Smalley
 * Date: 07/03/14
 */
@Service("timeSheetEntryTypeService")
public class TimeSheetEntryTypeService {
    @Resource
    private TimeSheetEntryTypeRepository timeSheetEntryTypeRepository;

    public List<TimeSheetEntryType> findAll() {
        return timeSheetEntryTypeRepository.findAll();
    }

    public TimeSheetEntryType save(TimeSheetEntryType timeSheetEntryType) {
        return timeSheetEntryTypeRepository.save(timeSheetEntryType);
    }

    public void deleteById(Long id) {
        timeSheetEntryTypeRepository.delete(timeSheetEntryTypeRepository.findById(id));
    }
}
