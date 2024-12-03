package com.servPet.adminPer.controller;

import com.servPet.adminPer.model.AdminPerService;
import com.servPet.adminPer.model.AdminPerVO;
import com.servPet.admin.model.AdminService;
import com.servPet.fnc.model.FncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/adminPer")
public class AdminPerController {

    @Autowired
    private AdminPerService adminPerSvc;

    @Autowired
    private AdminService adminSvc;  // 假設你有一個服務來管理Admin

    @Autowired
    private FncService fncService;  // 假設你有一個服務來管理Fnc

    // 顯示給管理員指派權限的頁面
    @GetMapping("addAdminPer")
    public String addAdminPer(ModelMap model) {
        model.addAttribute("adminPerVO", new AdminPerVO());
        model.addAttribute("adminListData", adminSvc.getAll());  // Assuming you have a method to get all admins
        model.addAttribute("fncList", fncService.getAll());  // Assuming you have a method to get all functions
        return "back_end/adminPer/addAdminPer";
    }

    //新增某個管理員的功能權限
    @PostMapping("insert")
    public String insert(@Validated AdminPerVO adminPerVO, BindingResult result, ModelMap model) {
        // 如果表單驗證有錯誤，返回錯誤訊息
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Please correct the errors in the form.");
            return "back_end/adminPer/addAdminPer";
        }

        // 新增 AdminPer
        adminPerSvc.addAdminPer(adminPerVO);

        // 顯示成功訊息並返回列表
        model.addAttribute("success", "- (Permission Added Successfully)");
        return "redirect:/adminPer/listAllAdminPer";  // 重定向到管理員權限列表頁面
    }

    // 列出所有管理員權限
    @PostMapping("listAdminPers_ByCompositeQuery")
    public String listAdminPers(HttpServletRequest req,Model model) {
    	Map<String, String[]> map = req.getParameterMap();
        List<AdminPerVO> list = adminPerSvc.getAll(map);
        model.addAttribute("adminPerListData", list);
        return "back_end/adminPer/listAllAdminPer";  // Page to show all admin permissions
    }

    // 顯示特定管理員權限的更新頁面
    @PostMapping("getOne_For_Update")
    public String getOneForUpdate(@RequestParam("adminPerId") Integer adminPerId, ModelMap model) {
        AdminPerVO adminPerVO = adminPerSvc.getOneAdminPer(adminPerId);
        if (adminPerVO == null) {
            model.addAttribute("errorMessage", "No such permission record found.");
            return "back_end/adminPer/listAllAdminPer";
        }
        model.addAttribute("adminPerVO", adminPerVO);
        model.addAttribute("adminListData", adminSvc.getAll());
        model.addAttribute("fncList", fncService.getAll());
        return "back_end/adminPer/updateAdminPer";
    }

    // 更新管理員權限
    @PostMapping("update")
    public String update(@Validated AdminPerVO adminPerVO, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Please correct the errors in the form.");
            return "back_end/adminPer/updateAdminPer";
        }

        // 更新管理員權限
        adminPerSvc.updateAdminPer(adminPerVO);

        // 顯示成功訊息並返回列表
        model.addAttribute("success", "- (Permission Updated Successfully)");
        return "redirect:/adminPer/listAllAdminPer";  // Redirect to the list of admin permissions
    }

    // 刪除特定的admin權限
    @PostMapping("delete")
    public String delete(@RequestParam("adminPerId") Integer adminPerId, ModelMap model) {
        adminPerSvc.deleteAdminPer(adminPerId);
        model.addAttribute("success", "- (Permission Deleted Successfully)");
        return "redirect:/adminPer/listAllAdminPer";  // Redirect to the list of admin permissions
    }

    // 透過複合查詢列出管理員權限
 // 這裡確保 listAdminPerByCompositeQuery 正確獲取資料
    	@PostMapping("listAdminPer_ByCompositeQuery")
    	public String listAdminPerByCompositeQuery(HttpServletRequest req, Model model) {
        Map<String, String[]> map = req.getParameterMap();
        List<AdminPerVO> adminPerList = adminPerSvc.getAll(map);  // 獲取所有符合條件的資料
        model.addAttribute("adminPerListData", adminPerList);  // 將結果添加到模型中
        return "back_end/adminPer/listAllAdminPer";  // 返回對應的頁面
    }
}
