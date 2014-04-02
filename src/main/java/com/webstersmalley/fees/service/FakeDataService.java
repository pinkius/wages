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

import com.webstersmalley.fees.domain.PayerType;
import com.webstersmalley.fees.domain.PaymentFrequency;
import com.webstersmalley.fees.domain.PaymentScheduleElement;
import com.webstersmalley.fees.domain.Resident;
import com.webstersmalley.fees.domain.ResidentAccount;
import com.webstersmalley.fees.domain.Room;
import com.webstersmalley.fees.domain.RoomBooking;
import com.webstersmalley.fees.domain.Transaction;
import com.webstersmalley.fees.domain.TransactionType;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.webstersmalley.fees.service.DataGenerationService.TODAY;

/**
 * Created: 30/03/2014
 */
@Service
public class FakeDataService {

    private boolean createdData = false;

    @Resource
    private DataGenerationService dataGenerationService;

    @Resource
    private ResidentService residentService;

    @Resource
    private RoomService roomService;

    @Resource
    private RoomBookingService roomBookingService;

    @Resource
    private TransactionService transactionService;

    @Resource
    private ResidentAccountService residentAccountService;


    /**
     * Creates and saves a room with a random number
     *
     * @return
     */
    private Room createFakeRoom() {
        Room room = new Room();
        room.setNumber(dataGenerationService.generateRoomNumber());
        roomService.save(room);
        return room;
    }

    /**
     * Creates and saves a fake resident
     * @return
     */
    protected Resident createFakeResident() {
        String name = dataGenerationService.generateName();
        LocalDate dateOfBirth = dataGenerationService.generateDateOfBirth();
        boolean active = true;
        LocalDate dateOfArrival = dataGenerationService.generateDateOfArrival();
        String contactName = dataGenerationService.generateName();
        String contactTelephone = dataGenerationService.generateTelephoneNumber();
        String niNumber = dataGenerationService.generateNINumber();
        String comment = dataGenerationService.generateComment();
        Resident resident = new Resident(name, dateOfBirth, active, dateOfArrival, contactName, contactTelephone, niNumber, comment);
        return residentService.save(resident);
    }

    private Resident createActiveCreditResident() {
        Resident resident = createActiveDeficitResident();
        ResidentAccount account = residentAccountService.getAccountForResident(resident);
        BigDecimal deficit = account.getBalance();
        BigDecimal payment = deficit.multiply(new BigDecimal("-1")).add(new BigDecimal("100.00"));
        Transaction transaction = new Transaction(resident, "back in black", DataGenerationService.TODAY, payment, TransactionType.PAYMENT);
        transactionService.save(transaction);
        return resident;
    }

    private Resident createActiveDeficitResident() {
        Resident resident = createFakeResident();
        Room room = createFakeRoom();
        List<RoomBooking> roomBookings = createFakeRoomBookingRange(resident, room, DataGenerationService.TODAY.minusMonths(1), DataGenerationService.TODAY, dataGenerationService.generateRoomRate());
        return resident;
    }

    private Resident createInactiveResident() {
        Resident resident = createFakeResident();
        Room room = createFakeRoom();
        List<RoomBooking> roomBookings = createFakeRoomBookingRange(resident, room, TODAY.minusMonths(3), TODAY.minusMonths(1), new BigDecimal("20.00"));
        BigDecimal amount = residentAccountService.getAccountForResident(resident).getBalance().multiply(new BigDecimal("-1"));
        Transaction transaction = new Transaction(resident, "Settling up", TODAY.minusMonths(1), amount, TransactionType.PAYMENT);
        transactionService.save(transaction);
        return resident;
    }

    private List<RoomBooking> createFakeRoomBookingRange(Resident resident, Room room, LocalDate start, LocalDate end, BigDecimal roomRate) {
        if (start.isAfter(end)) {
            return null;
        }
        List<RoomBooking> bookings = new ArrayList<>();
        LocalDate currentDate = start;
        while (currentDate.isBefore(end)) {
            bookings.add(createFakeRoomBooking(resident, room, currentDate, roomRate));
            currentDate = currentDate.plusDays(1);
        }
        return bookings;
    }

    /**
     * Creates a room booking for a specific resident/date and a random rate
     *
     * @param resident
     * @param room
     * @param date
     * @return
     */
    private RoomBooking createFakeRoomBooking(Resident resident, Room room, LocalDate date, BigDecimal roomRate) {
        RoomBooking roomBooking = new RoomBooking();
        roomBooking.setResident(resident);
        roomBooking.setRoom(room);
        roomBooking.setDate(date);
        roomBooking.setRoomRate(roomRate);
        return roomBookingService.save(roomBooking);

    }

    /**
     * Creates and saves a fake transaction (random amount) on the resident/date specified
     * @param resident
     * @param date
     * @param transactionType
     * @return
     */
    private Transaction createFakeTransaction(Resident resident, LocalDate date, TransactionType transactionType) {
        BigDecimal amount = new BigDecimal((int) (Math.random() * 50));
        String name;
        if (TransactionType.CHARGE == transactionType) {
            amount = amount.multiply(new BigDecimal("-1.00"));
            name = dataGenerationService.generateChargeType();
        } else {
            amount = amount.multiply(new BigDecimal("1.00"));
            name = "Payment";
        }
        Transaction transaction = new Transaction(resident, name, date, amount, transactionType);
        return transactionService.save(transaction);
    }

    /**
     * Creates all fake data once (NOT thread-safe).
     * Will create 3 residents:
     * - one inactive resident (their room bookings stop a month ago, and their account is balanced)
     * - one active resident with a balance in credit and a "simple" resident-only payment schedule
     * - one active resident with a balance in deficit and a "complex" resident/NoK/SS schedule
     */
    public void createFakeDataOnce() {
        if (!createdData) {
            createInactiveResident();
            Resident activeCreditResident = createActiveCreditResident();
            createPaymentSchedule(activeCreditResident, Collections.singletonList(PayerType.RESIDENT), Arrays.asList(PaymentFrequency.values()), new BigDecimal("460"));
            Resident activeDeficitResident = createActiveDeficitResident();
            createPaymentSchedule(activeDeficitResident, Arrays.asList(PayerType.values()), Arrays.asList(PaymentFrequency.values()), new BigDecimal("460"));
            createdData = true;
        }
    }

    /**
     * Creates a payment schedule made up of the payer types given (in order), with random amounts each, summing to
     * the total given.
     * The frequency of each schedule element is chosen randomly from the given frequencies. The start date is a random
     * date after the resident's arrival and the end date is null.
     * @param resident
     * @param payerTypes
     * @param paymentFrequencies
     * @param total
     * @return
     */
    private List<PaymentScheduleElement> createPaymentSchedule(Resident resident, List<PayerType> payerTypes, List<PaymentFrequency> paymentFrequencies, BigDecimal total) {
        List<PaymentScheduleElement> schedule = new ArrayList<>();
        BigDecimal remainder = total;
        for (PayerType payerType : payerTypes) {
            BigDecimal amount = dataGenerationService.generateBigDecimal(remainder);
            String name;
            if (PayerType.SOCIAL_SERVICES == payerType) {
                name = "Social Services";
            } else {
                name = dataGenerationService.generateName();
            }

            //schedule.add(new PaymentScheduleElement(resident, name, payerType, amount, dataGenerationService.generateRandomElementFromList(paymentFrequencies), from, to));
        }

        return schedule;
    }

    /**
     * Creates and saves a resident with a room booking, other charges and payments.
     * @return
     */
    public Resident createFakeResidentWithRoomAndCharges() {
        Room room = createFakeRoom();
        Resident resident = createFakeResident();
        for (int i = 0; i < 30; i++) {
            createFakeRoomBooking(resident, room, TODAY.minusDays(i), dataGenerationService.generateRoomRate());
        }
        for (int i = 0; i < 4; i++) {
            createFakeTransaction(resident, TODAY.minusWeeks(i), TransactionType.CHARGE);
            createFakeTransaction(resident, TODAY.minusWeeks(i), TransactionType.PAYMENT);
        }
        return resident;
    }


}
