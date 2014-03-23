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
