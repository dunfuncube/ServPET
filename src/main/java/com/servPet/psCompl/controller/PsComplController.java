package com.servPet.psCompl.controller;

import com.servPet.psCompl.model.PsComplVO;
import com.servPet.meb.model.MebService;
import com.servPet.meb.model.MebVO;
import com.servPet.psSvc.model.PsRepository;
import com.servPet.psSvc.model.PsService;
import com.servPet.ps.model.PsVO;
import com.servPet.psCompl.model.PsComplService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/psCompl")
public class PsComplController {

    @Autowired
    private PsComplService psComplSvc;
    
    @Autowired
    private PsService psSvc;
    
    @Autowired
    private MebService mebSvc;

    // 顯示新增檢舉頁面
    @GetMapping("addPsCompl")
    public String addPsCompl(ModelMap model) {
        // 修改为传递正确的属性名称，确保表单能够绑定
        model.addAttribute("psComplVO", new PsComplVO<Object>());
        return "back_end/psCompl/addPsCompl";  // 返回新增頁面
    }
    
    @PostMapping("insert")
    public String insertPsCompl(@Valid PsComplVO<?> psComplVO, BindingResult result,
                                 @RequestParam(value = "upFiles", required = false) MultipartFile[] files,
                                 @RequestParam(value = "mebId", required = true) Integer mebId,  // 添加 mebId 參數
                                 @RequestParam(value = "psId", required = true) Integer psId,    // 添加 psId 參數
                                 Model model) {

        // 如果表單驗證失敗，返回新增頁面並顯示錯誤信息
        if (result.hasErrors()) {
            model.addAttribute("error", "檢舉單提交失敗！");
            model.addAttribute("errors", result.getAllErrors());
            return "back_end/psCompl/addPsCompl";  // 返回新增頁面
        }

        // 設置檢舉日期與處理日期為當前時間
        LocalDateTime currentTime = LocalDateTime.now();
        psComplVO.setPsComplDate(currentTime);  // 設定檢舉日期
        psComplVO.setPsComplResDate(currentTime);  // 設定處理日期

        try {
            // 設置 PsComplVO 中的 psId 和 mebId
            psComplVO.setPsId(psId);  // 設定 PsVO 的 psId
            psComplVO.setMebId(mebId);  // 設定 MebVO 的 mebId
        } catch (Exception e) {
            model.addAttribute("error", "無法找到指定的會員或保母！");
            return "back_end/psCompl/addPsCompl";  // 返回新增頁面並顯示錯誤信息
        }

        // 如果有上傳檔案，處理檔案保存到對應的屬性
        try {
            if (files != null && files.length > 0) {
                // 調用 Service 層的檔案處理方法
                psComplSvc.handleFileUpload(psComplVO, files);
            }
        } catch (Exception e) {
            model.addAttribute("error", "檢舉圖片上傳失敗！");
            return "back_end/psCompl/addPsCompl";  // 返回新增頁面並顯示錯誤信息
        }

        // 調用 Service 層的新增方法，將資料保存到資料庫
        psComplSvc.addPsCompl(psComplVO);
        model.addAttribute("success", "檢舉提交成功");
		model.addAttribute("psComplListData",psComplSvc.getAll());
        return "redirect:/psCompl/listAllPsCompl";  // 成功後跳轉到檢舉列表頁面
    }


	// 顯示單一檢舉資料
    @PostMapping("getOne_For_Display")
    public String getOneForDisplay(@RequestParam("psComplId") String psComplIdStr, ModelMap model) {
        if (psComplIdStr == null || !psComplIdStr.matches("\\d+")) {
            model.addAttribute("errorMessage", "請提供有效的檢舉編號");
            return "back_end/psCompl/listAllPsCompl";  // 返回檢舉列表頁面
        }
        Integer psComplId = Integer.parseInt(psComplIdStr);

        // 查詢檢舉資料
        PsComplVO<?> psComplVO = psComplSvc.getOnePsCompl(psComplId);
        if (psComplVO == null) {
            model.addAttribute("errorMessage", "無此檢舉資料");
            return "back_end/psCompl/listAllPsCompl";  // 無此檢舉資料返回列表頁面
        }

        model.addAttribute("psComplVO", psComplVO);
        return "back_end/psCompl/listOnePsCompl";  // 返回顯示單一檢舉資料頁面
    }

    // 查詢所有檢舉資料
    @PostMapping("listAllPsCompl")
    public String listAllPsCompl(HttpServletRequest req, Model model) {
        Map<String, String[]> map = req.getParameterMap();
        List<PsComplVO> list = psComplSvc.getAll(map);  // 查詢所有檢舉資料
        model.addAttribute("psComplList", list);
        return "back_end/psCompl/listAllPsCompl";  // 顯示所有檢舉資料
    }

    // 顯示更新檢舉頁面
    @PostMapping("getOne_For_Update")
    public String getOneForUpdate(@RequestParam("psComplId") String psComplId, ModelMap model) {
        PsComplVO<?> PsComplVO = psComplSvc.getOnePsCompl(Integer.valueOf(psComplId));
        model.addAttribute("psComplVO", PsComplVO);  // 修改为正确的属性名称
        return "back_end/psCompl/update_psCompl_input";  // 返回更新檢舉頁面
    }

    // 更新檢舉資料
    @PostMapping("update")
    public String update(@Valid PsComplVO<?> psComplVO, BindingResult result, ModelMap model) {
        System.out.println("Before update PsComplDate: " + psComplVO.getPsComplDate());

        // 从数据库重新加载 PsComplVO，确保获取到完整的 psComplDate 等信息
        PsComplVO<?> updatedPsComplVO = psComplSvc.getOnePsCompl(psComplVO.getPsComplId());
        if (updatedPsComplVO == null) {
            model.addAttribute("error", "找不到檢舉資料，請稍後再試！");
            return "back_end/psCompl/list";  // 如果未找到资料，跳转回列表页
        }

        // 如果表单验证失败，返回更新页面并保留现有错误信息和数据
        if (result.hasErrors()) {
            model.addAttribute("psComplVO", updatedPsComplVO);
            model.addAttribute("psComplVO", psComplVO);  // 保留错误信息和用户输入的数据
            return "back_end/psCompl/update_psCompl_input";  // 返回更新页面
        }

        // 保证 psComplDate 不变，除非用户明确修改
        psComplVO.setPsComplDate(updatedPsComplVO.getPsComplDate());  // 保持数据库中的 psComplDate 值不变

        // 设置更新处理时间
        psComplVO.setPsComplResDate(LocalDateTime.now());

        // 更新数据
        psComplSvc.updatePsCompl(psComplVO); 

        // 显示修改成功消息
        model.addAttribute("success", "- (修改成功)");

        // 更新后重新查询并保持 psComplVO 到模型中
        psComplVO = psComplSvc.getOnePsCompl(psComplVO.getPsComplId());
        model.addAttribute("psComplVO", psComplVO);  // 保持 psComplVO 到模型中

        // 跳转到显示单一检举页面，并带上 psComplId
        return "back_end/psCompl/listOnePsCompl";  
    }

    // 檢舉資料
    @PostMapping("listByCompositeQuery")
    public String listByCompositeQuery(HttpServletRequest req, Model model) {
        Map<String, String[]> map = req.getParameterMap();
        List<PsComplVO> list = psComplSvc.getAll(map);  // 使用複合查詢過濾檢舉資料
        model.addAttribute("psComplList", list);
        return "back_end/psCompl/listAllPsCompl";  // 顯示過濾後的檢舉列表
    }
}
