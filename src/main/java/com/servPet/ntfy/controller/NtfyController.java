package com.servPet.ntfy.controller;

import com.servPet.ntfy.model.NtfyVO;
import com.servPet.ntfy.model.NtfyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

@Controller
@RequestMapping("/ntfy")
public class NtfyController {

    @Autowired
    private NtfyService ntfyService;
//    private NtfyRepository ntfyRepository;
    

    // 會員查看所有通知
    @GetMapping("/member/{mebId}")
    public String getMemberNotifications(@PathVariable Integer mebId, Model model) {
        List<NtfyVO> notifications = ntfyService.getAllNotificationsForMember(mebId);
        model.addAttribute("notifications", notifications);
        return "front_end/member-notifications";
    }

    // 會員查看未讀通知
    @GetMapping("/member/{mebId}/unread")
    public String getMemberUnreadNotifications(@PathVariable Integer mebId, Model model) {
        List<NtfyVO> notifications = ntfyService.getUnreadNotificationsForMember(mebId);
        model.addAttribute("notifications", notifications);
        model.addAttribute("mebId", mebId);
        return "front_end/member-notifications";
    }

    // 標記通知為已讀
    @PostMapping("/member/{mebId}/read/{ntfyMgmtId}")
    @ResponseBody
    public ResponseEntity<?> markAsRead(@PathVariable Integer mebId, @PathVariable Integer ntfyMgmtId) {
        try {
            ntfyService.markNotificationAsRead(ntfyMgmtId); // 更新資料庫狀態
            return ResponseEntity.ok().build(); // 返回成功
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    	// 		=========================== 後台操作 =========================== //

    @GetMapping("/admin")
    public String getAllNotifications(Model model) {
        List<NtfyVO> allNotifications = ntfyService.getAllNotifications();
        model.addAttribute("notifications", allNotifications);
        model.addAttribute("newNotification", new NtfyVO());  
        return "back_end/admin-notifications";
    }


    @PostMapping("/admin/create")
    public String createNotification(@Valid @ModelAttribute("newNotification") NtfyVO ntfy, 
                                     BindingResult result, 
                                     RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "新增通知失敗，請檢查輸入資料。");
            return "redirect:/ntfy/admin";
        }
        
        try {
            if (ntfy.getDate() == null) {
                ntfy.setDate(LocalDateTime.now());
            }
            ntfyService.createNotification(ntfy);
            redirectAttributes.addFlashAttribute("success", "通知新增成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "新增通知時發生錯誤：" + e.getMessage());
        }
        return "redirect:/ntfy/admin";
    }
    
    @GetMapping("/admin/edit/{ntfyMgmtId}")
    public String editNotificationForm(@PathVariable Integer ntfyMgmtId, Model model) {
        NtfyVO ntfy = ntfyService.getNotificationById(ntfyMgmtId);
        if (ntfy == null) {
            return "redirect:/ntfy/admin";
        }
        model.addAttribute("notification", ntfy);
        return "back_end/edit-notification";
    }

    
    @PostMapping("/admin/update")
    public String updateNotification(@Valid @ModelAttribute NtfyVO ntfy, BindingResult result) {
        if (result.hasErrors()) {
            return "back_end/edit-notification";
        }
        ntfyService.updateNotification(ntfy);
        return "redirect:/ntfy/admin";
    }

    

    @PostMapping("/admin/delete/{ntfyMgmtId}")
    public String deleteNotification(@PathVariable Integer ntfyMgmtId, 
                                     RedirectAttributes redirectAttributes) {
        try {
            ntfyService.deleteNotification(ntfyMgmtId);
            redirectAttributes.addFlashAttribute("success", "通知刪除成功！");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "刪除通知時發生錯誤：" + e.getMessage());
        }
        return "redirect:/ntfy/admin";
    }
}