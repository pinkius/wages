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

import com.webstersmalley.fees.domain.Room;
import com.webstersmalley.fees.repository.RoomRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created: 30/03/2014
 */
@Service
public class RoomService {
    @Resource
    private RoomRepository roomRepository;

    public Room save(Room room) {
        return roomRepository.save(room);
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public void delete(Long id) {
        roomRepository.delete(id);
    }

    public Room findById(Long roomId) {
        return roomRepository.findById(roomId);
    }
}
