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

import com.webstersmalley.fees.AbstractSpringAwareBase;
import com.webstersmalley.fees.domain.Room;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by: Matthew Smalley
 * Date: 02/04/14
 */
public class TestRoomService extends AbstractSpringAwareBase {
    @Test
    public void testSaveAndDelete() throws Exception {
        Room room = new Room(dataGenerationService.generateRoomNumber());
        List<Room> rooms = roomService.findAll();
        assertFalse(rooms.contains(room));
        roomService.save(room);
        rooms = roomService.findAll();
        assertTrue(rooms.contains(room));
        roomService.delete(room.getId());
        rooms = roomService.findAll();
        assertFalse(rooms.contains(room));

    }

    @Test
    public void testFindById() throws Exception {
        Room room = fakeDataService.createFakeRoom();
        assertEquals(room, roomService.findById(room.getId()));
    }
}
