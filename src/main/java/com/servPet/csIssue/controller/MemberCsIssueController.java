package com.servPet.csIssue.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.servPet.csIssue.model.CsIssueService;
import com.servPet.csIssue.model.CsIssueVO;
import com.servPet.meb.model.MebRepository;
import com.servPet.meb.model.MebVO;

@RequestMapping("/member/cs-issues")
@Controller
public class MemberCsIssueController {

    @Autowired
    private CsIssueService csIssueService;

    @Autowired
    private MebRepository memberRepository; // 使用 MemberRepository 直接操作會員數據

    // 假會員 ID（測試用）
    private static final Integer TEST_MEMBER_ID = 2002;

    /**
     * 新增問題頁面
     */
    @GetMapping("/add")
    public String showAddIssuePage(Model model) {
        // 從資料庫查詢假會員
        MebVO simulatedMember = memberRepository.findById(TEST_MEMBER_ID)
                .orElseThrow(() -> new RuntimeException("找不到會員 ID: " + TEST_MEMBER_ID));

        // 將會員信息傳遞到前端
        model.addAttribute("member", simulatedMember);

        return "front_end/cs_issues/addCsIssue"; // 返回新增頁面模板名稱
    }

    /**
     * 處理新增問題請求
     */
    @PostMapping("/insert")
    public String insertIssue(CsIssueVO csIssueVO) {
        // 從資料庫查詢假會員
        MebVO member = memberRepository.findById(TEST_MEMBER_ID)
                .orElseThrow(() -> new RuntimeException("找不到會員 ID: " + TEST_MEMBER_ID));

        // 关联会员信息并保存问题
        csIssueVO.setMebId(member);
        csIssueService.addCsIssue(csIssueVO);

        // 重定向到問題列表頁面
        return "redirect:/submission-complete";
    }
    
    @GetMapping("/submission-complete")
    public String showSubmissionCompletePage() {
        return "front_end/cs_issues/submissionComplete"; // 跳转页面模板路径
    }

    /**
     * 顯示當前會員的問題列表
     */
    @GetMapping("/list")
    public String listMemberIssues(Model model) {
        // 從資料庫查詢假會員
        MebVO member = memberRepository.findById(TEST_MEMBER_ID)
                .orElseThrow(() -> new RuntimeException("找不到會員 ID: " + TEST_MEMBER_ID));

        // 根據會員 ID 獲取問題列表
        List<CsIssueVO> issues = csIssueService.getByMember(member);
        model.addAttribute("issues", issues);
        model.addAttribute("member", member); // 傳遞會員信息供前端顯示

        return "front_end/cs_issues/listMemberIssues"; // 返回列表頁模板名稱
    }
}
