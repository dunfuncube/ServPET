package com.servPet.admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.servPet.admin.model.AdminVO;
import com.servPet.admin.model.AdminService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminSvc;

	// 顯示新增管理員頁面
	@GetMapping("addAdmin")
	public String addAdmin(ModelMap model) {
		model.addAttribute("adminVO", new AdminVO());
		return "back_end/admin/addAdmin";
	}

	// 新增管理員資料
	@PostMapping("insert")
	public String insert(@Valid AdminVO adminVO, BindingResult result, ModelMap model,
			@RequestParam("upFiles") MultipartFile[] parts) throws IOException {

		// 去除BindingResult中upFiles欄位的FieldError紀錄
		result = removeFieldError(adminVO, result, "upFiles");

		// 處理文件上傳
		if (parts.length == 0 || parts[0].isEmpty()) {
			model.addAttribute("errorMessage", "管理員照片: 請上傳照片");
		} else {
			// 將檔案轉換為 byte[] 存入 AdminVO
			adminVO.setUpFiles(parts[0].getBytes()); // 假設只處理單張上傳圖片
		}

		// 如果驗證錯誤或沒有選擇文件
		if (result.hasErrors() || parts.length == 0 || parts[0].isEmpty()) {
			return "back_end/admin/addAdmin"; // 返回新增頁面
		}

		// 新增管理員資料
		adminSvc.addAdmin(adminVO);

		// 顯示成功訊息並列出所有管理員
		model.addAttribute("adminListData", adminSvc.getAll());
		model.addAttribute("success", "- (新增成功)");
		return "redirect:/admin/listAllAdmin"; // 新增後重導至列表頁面
	}

	// 查詢單一管理員資料並跳轉至顯示頁面
	@PostMapping("getOne_For_Display")
	public String getOneForDisplay(@RequestParam("adminId") String adminIdStr, ModelMap model) {
		// 使用正則表達式檢查 adminId 是否為有效的數字
		if (adminIdStr == null || !adminIdStr.matches("\\d+")) {
			model.addAttribute("errorMessage", "請提供有效的管理員編號");
			return "back_end/admin/listAllAdmin"; // 返回管理員列表頁面
		}
		Integer adminId = Integer.parseInt(adminIdStr);

		AdminVO adminVO = adminSvc.getOneAdmin(adminId);
		if (adminVO == null) {
			model.addAttribute("errorMessage", "無此管理員資料");
			return "back_end/admin/listAllAdmin"; // 無此管理員則返回管理員列表頁面
		}

		model.addAttribute("adminVO", adminVO);
		return "back_end/admin/listOneAdmin"; // 返回顯示管理員資料的頁面
	}

	// 顯示更新功能頁面
	@PostMapping("getOne_For_Update")
	public String getOne_For_Update(@RequestParam("adminId") String adminId, ModelMap model) {
		/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
		/*************************** 2.開始查詢資料 *****************************************/
		// AdminService adminSvc = new AdminService();
		AdminVO adminVO = adminSvc.getOneAdmin(Integer.valueOf(adminId));
		/*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
		model.addAttribute("adminVO", adminVO);
		return "back_end/admin/update_admin_input"; // 查詢完成後轉交update_admin_input.html
	}

	// 更新管理員資料
	@PostMapping("update")
	public String update(@Valid AdminVO adminVO, BindingResult result, ModelMap model,
			@RequestParam("upFiles") MultipartFile[] parts) throws IOException {

		// 去除BindingResult中upFiles欄位的FieldError紀錄
		result = removeFieldError(adminVO, result, "upFiles");

		// 處理文件上傳
		if (parts.length == 0 || parts[0].isEmpty()) {
			// 如果沒有選擇新圖片，保留舊圖片
			byte[] upFiles = adminSvc.getOneAdmin(adminVO.getAdminId()).getUpFiles();
			adminVO.setUpFiles(upFiles);
		} else {
			// 上傳新圖片
			adminVO.setUpFiles(parts[0].getBytes()); // 假設只處理單張上傳圖片
		}

		// 如果驗證錯誤，返回更新頁面
		if (result.hasErrors()) {
			return "back_end/admin/update_admin_input";
		}

		// 更新管理員資料
		adminSvc.updateAdmin(adminVO);

		// 顯示更新成功訊息並重新查詢資料
		model.addAttribute("success", "- (修改成功)");
		adminVO = adminSvc.getOneAdmin(adminVO.getAdminId());
		model.addAttribute("adminVO", adminVO);
		return "back_end/admin/listOneAdmin"; // 更新後轉到管理員詳細頁面
	}

	// 複合查詢管理員資料
	@PostMapping("listAdmins_ByCompositeQuery")
	public String listAllAdmin(HttpServletRequest req, Model model) {
		Map<String, String[]> map = req.getParameterMap();
		List<AdminVO> list = adminSvc.getAll(map);
		model.addAttribute("adminListData", list);
		return "back_end/admin/listAllAdmin"; // 顯示所有管理員
	}

	// 去除BindingResult中某個欄位的FieldError紀錄
	private BindingResult removeFieldError(AdminVO adminVO, BindingResult result, String removedFieldname) {
		List<org.springframework.validation.FieldError> errorsListToKeep = result.getFieldErrors().stream()
				.filter(fieldname -> !fieldname.getField().equals(removedFieldname))
				.collect(java.util.stream.Collectors.toList());
		result = new org.springframework.validation.BeanPropertyBindingResult(adminVO, "adminVO");
		for (org.springframework.validation.FieldError fieldError : errorsListToKeep) {
			result.addError(fieldError);
		}
		return result;
	}

	@PostMapping("toggleStatus")
	public @ResponseBody Map<String, String> toggleStatus(@RequestParam("adminId") String adminIdStr) {
		Map<String, String> response = new HashMap<>();

		// 檢查 adminId 是否為有效數字
		if (adminIdStr == null || !adminIdStr.matches("\\d+")) {
			response.put("status", "error");
			return response; // 如果 adminId 不是數字，返回錯誤
		}

		Integer adminId = Integer.parseInt(adminIdStr);
		AdminVO adminVO = adminSvc.getOneAdmin(adminId);
		if (adminVO == null) {
			response.put("status", "error");
			return response; // 如果找不到該管理員，返回錯誤
		}

		System.out.println("Received adminId: " + adminId);

		// 切換管理員狀態
		String newStatus = adminVO.getAdminAccStatus().equals("1") ? "0" : "1";
		adminVO.setAdminAccStatus(newStatus); // 更新狀態
		adminSvc.updateAdmin(adminVO); // 儲存更新

		System.out.println("Updated status to: " + newStatus); // 日誌輸出

		// 返回更新後的狀態
		response.put("status", "success");
		response.put("newStatus", newStatus);
		return response;
	}
	
	 @GetMapping("/login")
	    public String loginPage(Model model) {
	        model.addAttribute("adminVO", new AdminVO());
	        return "login"; // 返回登入頁面
	    }

	    @PostMapping("/admin/login")
	    public String login(@ModelAttribute AdminVO adminVO, HttpSession session, Model model) {
	        AdminVO admin = adminSvc.login(adminVO.getAdminAcc(), adminVO.getAdminPwd());

	        if (admin != null) {
	            session.setAttribute("admin", admin); // 登入成功，保存用戶信息到 session
	            return "redirect:/index"; // 重定向到首頁
	        } else {
	            model.addAttribute("error", true); // 登入失敗
	            return "login"; // 返回登入頁面
	        }
	    }

	    @GetMapping("/logout")
	    public String logout(HttpSession session) {
	        session.invalidate(); // 注銷用戶
	        return "redirect:/login"; // 重定向到登入頁面
	    }
	}
