package com.servPet.event.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.servPet.event.model.EventService;
import com.servPet.event.model.EventVO;

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/select")
    public String showSelectPage(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "back_end/event/select_page";
    }

    @GetMapping("/display")
    public String displayEvent(@RequestParam("infid") Integer infid, Model model, RedirectAttributes redirectAttributes) {
        return getEventOrRedirect(infid, model, redirectAttributes, "back_end/event/listOneEvent");
    }

    @GetMapping("/addPage")
    public String showAddEventPage(Model model) {
        model.addAttribute("eventVO", new EventVO());
        return "back_end/event/addEvent";
    }

    @PostMapping("/add")
    public String addEvent(@Valid @ModelAttribute EventVO eventVO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorMsg", "新增失敗：資料不完整");
            return "redirect:/event/addPage";
        }
        return handleEventOperation(() -> eventService.addEvent(eventVO), 
            "活動新增成功", "新增活動失敗", "redirect:/event/list", redirectAttributes);
    }

    @GetMapping("/edit")
    public String editEvent(@RequestParam("infid") Integer infId, Model model, RedirectAttributes redirectAttributes) {
        return getEventOrRedirect(infId, model, redirectAttributes, "back_end/event/updateEventInput");
    }

    @PostMapping("/update")
    public String updateEvent(@ModelAttribute EventVO eventVO, RedirectAttributes redirectAttributes) {
        return handleEventOperation(() -> eventService.updateEvent(
            eventVO.getInfId(), eventVO.getTitle(), eventVO.getContent(), eventVO.getInfType()), 
            "活動更新成功", "更新失敗", "redirect:/event/display?infid=" + eventVO.getInfId(), redirectAttributes);
    }

    @PostMapping("/unpublish")
    public String unpublishEvent(@RequestParam("infid") Integer infid, RedirectAttributes redirectAttributes) {
        EventVO eventVO = eventService.getEventById(infid);
        if (eventVO == null || eventVO.getInfType() == 99) {
            redirectAttributes.addFlashAttribute("errorMsg", "活動已下架或不存在");
            return "redirect:/event/list";
        }
        return handleEventOperation(() -> eventService.unpublishEvent(infid), 
            "活動下架成功", "下架失敗", "redirect:/event/list", redirectAttributes);
    }

    @GetMapping("/list")
    public String listAllEvents(Model model) {
        model.addAttribute("events", eventService.getAllEvents());
        return "back_end/event/listAllEvent";
    }

    private String getEventOrRedirect(Integer infid, Model model, RedirectAttributes redirectAttributes, String successView) {
        try {
            model.addAttribute("eventVO", eventService.getEventById(infid));
            return successView;
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("errorMsg", "操作失敗：" + e.getMessage());
            return "redirect:/event/select";
        }
    }

    private String handleEventOperation(Runnable operation, String successMsg, String errorMsg, 
                                        String redirectPath, RedirectAttributes redirectAttributes) {
        try {
            operation.run();
            redirectAttributes.addFlashAttribute("successMsg", successMsg);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMsg", errorMsg + "：" + e.getMessage());
        }
        return redirectPath;
    }
}

