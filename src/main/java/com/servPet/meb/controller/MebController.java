package com.servPet.meb.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.servPet.meb.model.MebService;
import com.servPet.meb.model.MebVO;

@Controller
@RequestMapping("/front_end")
public class MebController {

	@Autowired
	private MebService mebService;
//
//	@Autowired
//	BCryptPasswordEncoder passwordEncoder;
	// 首頁
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("message", "歡迎光臨！");
		return "front_end/home";
	}

	// 顯示註冊頁面
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("member", new MebVO());
		return "front_end/register";
	}

	// 處理註冊請求

	@PostMapping("/register")
	public String registerMember(MebVO member, Model model) {
	    try {
//	        member.setMebPwd(passwordEncoder.encode(member.getMebPwd()));
	        member.setMebPwd((member.getMebPwd()));
	        mebService.addMember(member);
	        model.addAttribute("message", "註冊成功！");
	        return "front_end/home";
	    }catch (IllegalArgumentException e) {
	        e.printStackTrace(); // 在後台打印錯誤日誌
	        model.addAttribute("error", "註冊失敗：" + e.getMessage());
	        model.addAttribute("member", member);
	        return "front_end/register";
	    }
	}


	// 顯示登入頁面
	@GetMapping("/login")
	public String showLoginPage() {
		return "front_end/login";
	}

	// 處理登入請求
	@PostMapping("/login")
	public String loginMember(@RequestParam String mebMail, @RequestParam String mebPwd, Model model) {
		Optional<MebVO> member = mebService.getMemberByEmail(mebMail);

		if (member.isPresent() && mebService.checkPassword(mebPwd, member.get().getMebPwd())) {
			model.addAttribute("message", "登入成功！");
			model.addAttribute("member", member.get());
			return "front_end/dashboard";
		} else {
			model.addAttribute("error", "登入失敗，請檢查帳號或密碼！");
			return "front_end/login";
		}
	}

	// 顯示忘記密碼頁面
	@GetMapping("/forgot-password")
	public String showForgotPasswordPage() {
		return "front_end/forgot-password";
	}

	// 處理忘記密碼請求
	@PostMapping("/forgot-password")
	public String forgotPassword(@RequestParam String mebMail, Model model) {
		Optional<MebVO> member = mebService.getMemberByEmail(mebMail);

		if (member.isPresent()) {
			// 模擬發送重置郵件的邏輯
			model.addAttribute("message", "密碼提示已發送至您的電子郵件！");
		} else {
			model.addAttribute("error", "該電子郵件未註冊！");
		}
		return "front_end/forgot-password";
	} // 檢查電子郵件是否已被使用
	@PostMapping("/checkEmail")
	@ResponseBody
	public Map<String, Boolean> checkEmail(@RequestBody Map<String, String> request) {
	    String email = request.get("email");
	    boolean exists = mebService.getMemberByEmail(email).isPresent();
	    Map<String, Boolean> response = new HashMap<>();
	    response.put("exists", exists);
	    return response;
	}
}
