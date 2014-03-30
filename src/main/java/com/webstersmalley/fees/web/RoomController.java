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
public class RoomController {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private RoomService roomService;
    @RequestMapping(value = "/rooms")
     public ModelAndView roomsPage() {
         ModelAndView mav = new ModelAndView("rooms");
         List<Room> rooms = roomService.findAll();
         logger.info("Returning: {} rooms", rooms.size());
         mav.addObject("rooms", rooms);
         return mav;
     }



     @RequestMapping(value = "/saveRoom")
     public String saveroom(@RequestParam Long id, @RequestParam String number) {
         Room room = new Room();
         room.setId(id);
         room.setNumber(number);

         logger.info("Saving room: {}", room);
         roomService.save(room);
         return "redirect:rooms.html";
     }

     @RequestMapping(value = "/deleteRoom")
     public String deleteRoom(@RequestParam Long id) {
         roomService.delete(id);
         return "redirect:rooms.html";
     }
}
