package com.servPet.ps.controller;

import com.servPet.ps.model.PsVO;
import com.servPet.ps.model.PsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/ps")
public class PsController {

    @Autowired
    private PsService psService;

    @GetMapping("/list")
    public String listAllPs(Model model) {
        List<PsVO> psList = psService.getAll();
        model.addAttribute("psList", psList);
        return "back_end/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("psVO", new PsVO());
        return "back_end/add";
    }

    @PostMapping("/add")
    public String addPs(@ModelAttribute PsVO psVO) {
        psService.addPs(psVO);
        return "redirect:/ps/list";
    }

    @GetMapping("/edit/{psId}")
    public String showEditForm(@PathVariable("psId") Integer psId, Model model) {
        PsVO psVO = psService.getOnePs(psId);
        model.addAttribute("psVO", psVO);
        return "back_end/edit";
    }

    @PostMapping("/edit")
    public String editPs(@ModelAttribute PsVO psVO) {
        psService.update(psVO);
        return "redirect:/ps/list";
    }

    @GetMapping("/delete/{psId}")
    public String deletePs(@PathVariable("psId") Integer psId) {
        psService.deletePs(psId);
        return "redirect:/ps/list";
    }

    @GetMapping("/ps/search")
    public String searchPs(
            @RequestParam(required = false) Integer psId,
            @RequestParam(required = false) String psName,
            @RequestParam(required = false) String psArea,
            @RequestParam(required = false) Integer totalStars,
            @RequestParam(required = false) Integer ratingTimes,
            @RequestParam(required = false) Integer reportTimes,
            Model model) {

        List<PsVO> psList = psService.searchPs(psId, psName, psArea, totalStars, ratingTimes, reportTimes);
        model.addAttribute("psList", psList);
        return "ps/list";
    }
}
