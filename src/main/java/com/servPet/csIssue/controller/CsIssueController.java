package com.servPet.csIssue.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.servPet.admin.model.AdminVO;
import com.servPet.csIssue.model.CsIssueService;
import com.servPet.csIssue.model.CsIssueVO;

@RequestMapping("/cs-issues")
@Controller
public class CsIssueController {

	@Autowired
	private CsIssueService csIssueService;

//    @GetMapping("/")
//    public String show() {
//        return "index";
//    }
	
	

	@GetMapping("/list")
	public String listAllCsIssues(
	        @RequestParam(defaultValue = "0") int page,
	        @RequestParam(defaultValue = "5") int size,
	        Model model) {
	    
	    // 如果 size 為 0 或小於 0，則不進行分頁，返回全部數據
	    if (size <= 0) {
	        List<CsIssueVO> issues = csIssueService.getAllOrderedByStatusAndMemberId();
	        model.addAttribute("issues", issues);
	    } else {
	        // 啟用分頁
	        Pageable pageable = PageRequest.of(page, size);
	        Page<CsIssueVO> issuesPage = csIssueService.getAllWithPagination(pageable);

	        model.addAttribute("issues", issuesPage.getContent());
	        model.addAttribute("totalPages", issuesPage.getTotalPages());
	        model.addAttribute("currentPage", page);
	        model.addAttribute("size", size);
	    }

	    return "back_end/cs_issues/listCsIssues"; // Thymeleaf 模板名稱
	}



	@GetMapping("/view/{id}")
	public String viewCsIssue(@PathVariable Integer id, Model model) {
		
//		 System.out.println("接收到的問題 ID：" + id);
		
		CsIssueVO csIssueVO = csIssueService.getOneCsIssue(id);
		model.addAttribute("csIssueVO", csIssueVO);
		return "back_end/cs_issues/viewCsIssue"; // Thymeleaf 模板名稱
	}

	@GetMapping("/edit/{id}")
	public String showEditCsIssuePage(@PathVariable Integer id, Model model) {
		CsIssueVO csIssueVO = csIssueService.getOneCsIssue(id);
		model.addAttribute("csIssueVO", csIssueVO);
		return "back_end/cs_issues/editCsIssue"; // Thymeleaf 模板名稱
	}



	@PostMapping("/reply")
    public String replyCsIssue(
    	
    		
            @RequestParam Integer csQaId,
            @RequestParam String reContent,
            Model model) {
		
		 System.out.println("接收到的問題 ID：" + csQaId);
   		 System.out.println("接收到的回覆內容：" + reContent);
		 
        try {
            // 獲取問題
            CsIssueVO csIssue = csIssueService.getOneCsIssue(csQaId);

            // 獲取當前管理員 ID
            Integer adminId = 1001; // 替換為從 session 獲取的 ID
            AdminVO admin = csIssueService.getAdminById(adminId);

            // 更新問題信息
            csIssue.setAdminId(admin);
            csIssue.setReContent(reContent);
            csIssue.setReDate(LocalDateTime.now());
            csIssue.setQaSta("1");


            // 保存更新
            csIssueService.addCsIssue(csIssue);
            model.addAttribute("successMessage", "問題已成功回覆");

            // 重導向至詳細資料頁面
            return "redirect:/cs-issues/view/" + csQaId;
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", "回覆失敗：" + e.getMessage());
            return "redirect:/cs-issues/list";
        }
    }

	@GetMapping("/addReply/{id}")
	public String showAddReplyPage(@PathVariable Integer id, Model model) {
		CsIssueVO csIssueVO = csIssueService.getOneCsIssue(id);
		model.addAttribute("csIssueVO", csIssueVO);

//		System.out.println("獲取的問題內容：" + csIssueVO);

		return "back_end/cs_issues/addReply"; // 回覆表單頁面模板
	}

}
