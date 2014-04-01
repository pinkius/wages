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

package com.webstersmalley.fees.service;

import com.webstersmalley.fees.domain.Resident;
import com.webstersmalley.fees.domain.ResidentAccount;
import com.webstersmalley.fees.domain.RoomBooking;
import com.webstersmalley.fees.domain.Transaction;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created: 30/03/2014
 */
@Service
public class ResidentAccountService {
    @Resource
    private ResidentService residentService;
    @Resource
    private RoomBookingService roomBookingService;
    @Resource
    private TransactionService transactionService;

    public ResidentAccount getAccountForResident(Resident resident) {
        List<RoomBooking> roomBookings = roomBookingService.findAllByResident(resident);
        List<Transaction> transactions = transactionService.findByResident(resident);

        for (RoomBooking roomBooking : roomBookings) {
            BigDecimal amount = roomBooking.getRoomRate().multiply(new BigDecimal("-1"));
            Transaction transaction = new Transaction(resident, "Room charge", roomBooking.getDate(), amount, Transaction.TransactionType.CHARGE);
            transactions.add(transaction);
        }

        ResidentAccount account = new ResidentAccount();
        account.setResident(resident);
        account.setTransactions(transactions);

        return account;
    }

    public List<ResidentAccount> getAllResidentAccountSummaries() {
        List<Resident> residents = residentService.findAll();
        List<ResidentAccount> accounts = new ArrayList<ResidentAccount>();
        for (Resident resident : residents) {
            accounts.add(getAccountForResident(resident));
        }
        return accounts;
    }
}
