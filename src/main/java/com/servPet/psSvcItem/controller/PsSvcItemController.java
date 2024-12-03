package com.servPet.psSvcItem.controller;

import com.servPet.psSvcItem.model.PsSvcItemService;
import com.servPet.psSvcItem.model.PsSvcItemVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/psSvcItem")
public class PsSvcItemController {

    @Autowired
    private PsSvcItemService psSvcItemService;

    @GetMapping("/list")
    public String listAllPsSvcItem(Model model) {
        List<PsSvcItemVO> list = psSvcItemService.getAll();
        model.addAttribute("list", list);
        return "back_end/listAllPsSvcItem";
    }
    @PostMapping("/update")
    public String getOneForUpdate(@RequestParam("svcId") Integer svcId, Model model) {
        PsSvcItemVO psSvcItemVO = psSvcItemService.getPsSvcItemById(svcId);
        model.addAttribute("psSvcItemVO", psSvcItemVO);
        return "back_end/update_psSvcItem_input";
    }

    @PostMapping("/delete")
    public String deletePsSvcItem(@RequestParam("svcId") Integer svcId) {
        psSvcItemService.deletePsSvcItem(svcId);
        return "redirect:/psSvcItem/list";
    }

}

