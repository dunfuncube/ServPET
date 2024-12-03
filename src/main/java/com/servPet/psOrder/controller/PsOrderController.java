package com.servPet.psOrder.controller;

import com.servPet.ps.model.PsVO;
import com.servPet.psOrder.model.PsOrderService;
import com.servPet.psOrder.model.PsOrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/psorder")
public class PsOrderController {
    
    @Autowired
    private PsOrderService psOrderService;

    @GetMapping("list")
    public String listAllPsOrder(Model model) {
        List<PsOrderVO> psOrderList = psOrderService.getAll();
        model.addAttribute("psOrderList", psOrderList);
        return "back_end/list";
    }
}
