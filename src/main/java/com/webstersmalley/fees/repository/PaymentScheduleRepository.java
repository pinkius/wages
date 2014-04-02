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

package com.webstersmalley.fees.repository;

import com.webstersmalley.fees.domain.PaymentScheduleElement;
import com.webstersmalley.fees.domain.Resident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by: Matthew Smalley
 * Date: 01/04/14
 */
@Transactional(readOnly = true)
public interface PaymentScheduleRepository extends CrudRepository<PaymentScheduleElement, Long> {
    List<PaymentScheduleElement> findByResident(Resident resident);

    @Transactional(readOnly = false)
    PaymentScheduleElement save(PaymentScheduleElement paymentScheduleElement);
}
