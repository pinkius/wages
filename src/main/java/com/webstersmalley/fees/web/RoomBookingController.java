package com.webstersmalley.fees.web;/*************************************************************************
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
import com.webstersmalley.fees.domain.RoomBooking;
import com.webstersmalley.fees.service.RoomBookingService;
import com.webstersmalley.fees.service.RoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created: 30/03/2014
 */
@Controller
public class RoomBookingController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private RoomBookingService roomBookingService;

    @Resource
    private RoomService roomService;

    @RequestMapping("/roomBookings")
    public ModelAndView getRoomBookingsByRoom(@RequestParam Long roomId) {

        ModelAndView mav = new ModelAndView("roomBookings");
        Room room = roomService.findById(roomId);
        mav.addObject("room", room);
        List<RoomBooking> roomBookings = roomBookingService.findAllByRoom(room);
        logger.info("Returning: {} roomBookings", roomBookings.size());
        mav.addObject("roomBookings", roomBookings);
        return mav;
    }

}
