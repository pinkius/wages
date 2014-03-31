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

package com.webstersmalley.fees.service;import com.webstersmalley.fees.domain.Resident;
import com.webstersmalley.fees.domain.Room;
import com.webstersmalley.fees.domain.RoomBooking;
import com.webstersmalley.fees.domain.Transaction;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created: 30/03/2014
 */
@Service
public class FakeDataService {
    private static final LocalDate TODAY = new LocalDate();
    private static final String[] FIRST_NAMES = {"Madeleine", "Amelia", "Oscar", "Peter", "Libby", "Daisy", "Elsie", "Ruby"};
    private static final String[] LAST_NAMES = {"Turnbull", "Gray", "Barlow", "Adams", "Pope", "Perkins", "Gordon"};
    private static final String[] CHARGE_TYPES = {"Hairdressing", "Escort", "Chiropadist"};

    private boolean createdData = false;

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

    private String getRandomString(String[] selection) {
            return selection[(int) (Math.random() * selection.length)];
        }

    /**
     * Creates and saves a room with a random number
     *
     * @return
     */
    public Room createFakeRoom() {
        Room room = new Room();
        room.setNumber(new Integer((int)(Math.random() * 1000)).toString());
        roomService.save(room);
        return room;
    }

    /**
     * Creates and saves a fake resident
     * @return
     */
    public Resident createFakeResident() {
        Resident resident = new Resident();
        resident.setName(getRandomString(FIRST_NAMES) + " " + getRandomString(LAST_NAMES));
        resident.setDateOfBirth(TODAY.minusDays((int) (Math.random() * 120 * 365)));
        resident.setActive(true);
        return residentService.save(resident);
    }


    /**
     * Creates all fake data once (NOT thread-safe).
     * Will create 3 residents:
     * - one inactive resident (their room bookings stop a month ago, and their account is balanced)
     * - one active resident with a balance in credit
     * - one active resident with a balance in deficit
     */
    private void createFakeData() {
        createInactiveResident();
        createFakeResidentWithRoomAndCharges();
    }

    private void createInactiveResident() {
        Resident resident = createFakeResident();
        Room room = createFakeRoom();
        List<RoomBooking> roomBookings = createFakeRoomBookingRange(resident, room, TODAY.minusMonths(3), TODAY.minusMonths(1), new BigDecimal("20.00"));
        Transaction transaction = new Transaction();
        transaction.setResident(resident);
        transaction.setName("Settling up");
        transaction.setAmount(residentAccountService.getAccountForResident(resident).getBalance().multiply(new BigDecimal("-1")));
        transaction.setDate(TODAY.minusMonths(1));
        transactionService.save(transaction);
    }

    public List<RoomBooking> createFakeRoomBookingRange(Resident resident, Room room, LocalDate start, LocalDate end, BigDecimal roomRate) {
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
    public RoomBooking createFakeRoomBooking(Resident resident, Room room, LocalDate date, BigDecimal roomRate) {
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
    public Transaction createFakeTransaction(Resident resident, LocalDate date, Transaction.TransactionType transactionType) {
        Transaction transaction = new Transaction();
        transaction.setResident(resident);
        transaction.setDate(date);
        if (Transaction.TransactionType.CHARGE == transactionType) {
            transaction.setAmount(new BigDecimal((int)(Math.random()*50)).multiply(new BigDecimal("-1.00")));
            transaction.setName(getRandomString(CHARGE_TYPES));
        }
        if (Transaction.TransactionType.PAYMENT == transactionType) {
            transaction.setAmount(new BigDecimal((int)(Math.random()*150)).multiply(new BigDecimal("1.00")));
            transaction.setName("Payment");
        }

        transaction.setTransactionType(transactionType);
        return transactionService.save(transaction);
    }

    /**
     * Creates all fake data once (NOT thread-safe).
     */
    public void createFakeDataOnce() {
        if (!createdData) {
            createFakeData();
            createdData = true;
        }
    }

    /**
     * Creates and saves a resident with a room booking, other charges and payments.
     * @return
     */
    public Resident createFakeResidentWithRoomAndCharges() {
        Room room = createFakeRoom();
        Resident resident = createFakeResident();
        for (int i = 0; i < 30; i++) {
            createFakeRoomBooking(resident, room, TODAY.minusDays(i), new BigDecimal("10.00"));
        }
        for (int i = 0; i < 4; i++) {
            createFakeTransaction(resident, TODAY.minusWeeks(i), Transaction.TransactionType.CHARGE);
            createFakeTransaction(resident, TODAY.minusWeeks(i), Transaction.TransactionType.PAYMENT);
        }
        return resident;
    }
}
