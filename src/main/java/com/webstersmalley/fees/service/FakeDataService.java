package com.webstersmalley.fees.service;/*************************************************************************
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
import com.webstersmalley.fees.domain.Room;
import com.webstersmalley.fees.domain.RoomBooking;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * Created: 30/03/2014
 */
@Service
public class FakeDataService {
    private static final String[] FIRST_NAMES = {"Madeleine", "Amelia", "Oscar", "Peter", "Libby", "Daisy", "Elsie", "Ruby"};
    private static final String[] LAST_NAMES = {"Turnbull", "Gray", "Barlow", "Adams", "Pope", "Perkins", "Gordon"};

    private boolean createdData = false;

    @Resource
    private ResidentService residentService;

    @Resource
    private RoomService roomService;

    @Resource
    private RoomBookingService roomBookingService;

    private String getRandomString(String[] selection) {
            return selection[(int) (Math.random() * selection.length)];
        }

    public Room createFakeRoom() {
        Room room = new Room();
        room.setNumber(new Integer((int)(Math.random() * 1000)).toString());
        roomService.save(room);
        return room;
    }

    public Resident createFakeResident() {
        Resident resident = new Resident();
        resident.setName(getRandomString(FIRST_NAMES) + " " + getRandomString(LAST_NAMES));
        resident.setDateOfBirth(new LocalDate().minusDays((int) (Math.random() * 120 * 365)));
        resident.setActive(true);
        return residentService.save(resident);
    }
    public void createFakeData() {
        Resident resident = createFakeResident();
        Room room = createFakeRoom();
        createFakeRoomBooking(resident, room);

    }

    private RoomBooking createFakeRoomBooking(Resident resident, Room room) {
        RoomBooking roomBooking = new RoomBooking();
        roomBooking.setResident(resident);
        roomBooking.setRoom(room);
        roomBooking.setDate(new LocalDate());
        roomBooking.setFee(new BigDecimal("10.00"));
        return roomBookingService.save(roomBooking);

    }

    public void addFakeDataOnce() {
        if (!createdData) {
            createFakeResident();
            createdData = true;
        }
    }
}
