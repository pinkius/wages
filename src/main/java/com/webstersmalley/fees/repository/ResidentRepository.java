package com.webstersmalley.fees.repository;/*************************************************************************
 Copyright 2011 Webstersmalley

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
 *************************************************************************/

import com.webstersmalley.fees.domain.Resident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created: 30/03/2014
 */
@Transactional(readOnly = true)
public interface ResidentRepository extends CrudRepository<Resident, Long> {
    List<Resident> findByName(String lastName);

    List<Resident> findAll();

    @Transactional(readOnly = false)
    Resident save(Resident employee);

    Resident findById(Long id);

    @Transactional(readOnly = false)
    void delete(Resident employee);
}
