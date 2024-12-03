package com.servPet.vtr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.servPet.vtr.model.VtrService;
import com.servPet.vtr.model.VtrVO;

@Controller
@RequestMapping("/vtr")
public class VtrController {

    @Autowired
	private VtrService vtrService;

    // 顯示所有 VtrVO
    @GetMapping
    public String listVtrVO(Model model) {
        model.addAttribute("vtrVOs", vtrService.getAllVtrVOs());
        return "vtr";  // 显示所有 VtrVO 的列表，视图为 'vtr.html'
    }

    // 顯示 VtrVO 詳情
    @GetMapping("/view/{id}")
    public String viewVtrVO(@PathVariable Integer id, Model model) {
        vtrService.getVtrVOById(id).ifPresent(vtrVO -> model.addAttribute("vtrVO", vtrVO));
        return "view";  // 显示指定 VtrVO 的详细信息，视图为 'view.html'
    }

    // 顯示創建頁面
    @GetMapping("/create")
    public String createVtrVOForm(Model model) {
        model.addAttribute("vtrVO", new VtrVO());
        return "vtrcreate";  // 显示创建表单页面，视图为 'vtrcreate.html'
    }

    // 處理創建 VtrVO
    @PostMapping("/create")
    public String createVtrVO(@ModelAttribute VtrVO vtrVO, Model model) {
        // 检查 money 是否为负数
        if (vtrVO.getMoney() < 0) {
            model.addAttribute("errorMessage", "Money cannot be negative.");
            return "vtrcreate";  // 返回创建页面并显示错误
        }
        vtrService.saveVtrVO(vtrVO);
        return "redirect:/vtr";  // 创建成功后重定向到列表页面
    }

    // 處理刪除 VtrVO
    @GetMapping("/delete/{id}")
    public String deleteVtrVO(@PathVariable Integer id) {
        vtrService.deleteVtrVO(id);
        return "redirect:/vtr";  // 删除后重定向到列表页面
    }
}
