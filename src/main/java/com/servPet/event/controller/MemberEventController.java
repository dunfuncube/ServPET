package com.servPet.event.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.servPet.event.model.EventService;
import com.servPet.event.model.EventVO;

@Controller
@RequestMapping("/member/event")
public class MemberEventController { @Autowired
    private EventService eventService;

	@GetMapping("/")
	public String show() {
	  return "front_end/event/index";
	}

    @GetMapping("/list")
    public String listAllEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "front_end/event/listAllEvent";
    }

    @GetMapping("/display")
    public String displayEvent(@RequestParam("infid") Integer infId, Model model) {
        EventVO eventVO = eventService.getEventById(infId);
        model.addAttribute("eventVO", eventVO);
        return "front_end/event/displayEvent";
    }
}