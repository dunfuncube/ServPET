package com.servPet.fnc.controller;

import com.servPet.fnc.model.FncVO;
import com.servPet.fnc.model.FncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/fnc")
public class FncController {

    @Autowired
    private FncService fncSvc;

    // 顯示新增功能頁面
    @GetMapping("addFnc")
    public String addFnc(ModelMap model) {
        model.addAttribute("fncVO", new FncVO());
        return "back_end/fnc/addFnc"; // 頁面名稱為 addFnc.html	
    }

 @PostMapping("insert")
 public String insert(@Valid FncVO fncVO, BindingResult result, ModelMap model) {

     // 如果驗證錯誤，返回新增頁面
     if (result.hasErrors()) {
         return "back_end/fnc/addFnc";
     }
     
     // 設定創建時間與更新時間為當前時間
     LocalDateTime currentTime = LocalDateTime.now();
     fncVO.setFncCreAt(currentTime);  // 設定創建時間
     fncVO.setFncUpdAt(currentTime);  // 設定更新時間
     
     // 新增功能資料
     fncSvc.addFnc(fncVO);
     
     model.addAttribute("success", "- (新增成功)");
     return "redirect:/fnc/listAllFnc";  // 新增後重導至功能列表頁面
 }


 @PostMapping("getOne_For_Display")
 public String getOneForDisplay(@RequestParam("fncId") String fncIdStr, ModelMap model) {
     // 檢查 fncId 是否有效
     if (fncIdStr == null || !fncIdStr.matches("\\d+")) {
         model.addAttribute("errorMessage", "請提供有效的功能編號");
         return "back_end/fnc/listAllFnc";  // 返回功能列表頁面
     }
     Integer fncId = Integer.parseInt(fncIdStr);

     // 查詢功能資料
     FncVO fncVO = fncSvc.getOneFnc(fncId);
     if (fncVO == null) {
         model.addAttribute("errorMessage", "無此功能資料");
         return "back_end/fnc/listAllFnc";  // 無此功能則返回功能列表頁面
     }

     model.addAttribute("fncVO", fncVO);
     return "back_end/fnc/listOneFnc";  // 返回顯示功能資料頁面
 }

    // 查詢所有功能資料
    @PostMapping("listAllFnc_ByCompositeQuery")
    public String listAllFnc(HttpServletRequest req,Model model) {
    	Map<String, String[]> map = req.getParameterMap();
        List<FncVO> list = fncSvc.getAll(map);  // 查詢所有功能資料
        model.addAttribute("fncList", list);
        return "back_end/fnc/listAllFnc";  // 顯示所有功能
    }

    // 顯示更新功能頁面
    @PostMapping("getOne_For_Update")
    public String getOneForUpdate(@RequestParam("fncId") String fncId, ModelMap model) {
    	/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
        FncVO fncVO = fncSvc.getOneFnc(Integer.valueOf(fncId));
		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
        model.addAttribute("fncVO", fncVO);
        return "back_end/fnc/update_fnc_input"; // 返回更新功能頁面
    }

    // 更新功能資料
    @PostMapping("update")
    public String update(@Validated FncVO fncVO, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "back_end/fnc/updateFnc";
        }

        // 更新時，設定更新時間為當前時間
        fncVO.setFncUpdAt(LocalDateTime.now());  // 設定更新時間
        
        // 更新功能資料
        fncSvc.updateFnc(fncVO);
        
        model.addAttribute("success", "- (修改成功)");
        model.addAttribute("fncVO", fncVO);
        return "back_end/fnc/listOneFnc";  // 更新後轉到顯示功能資料頁面
    }


    // 複合查詢功能
    @PostMapping("listByCompositeQuery")
    public String listByCompositeQuery(HttpServletRequest req, Model model) {
        Map<String, String[]> map = req.getParameterMap();
        List<FncVO> fncList = fncSvc.getAll(map);  // 使用複合查詢過濾功能資料
        model.addAttribute("fncList", fncList);
        return "back_end/fnc/listAllFnc";  // 顯示過濾後的功能列表
    }
}