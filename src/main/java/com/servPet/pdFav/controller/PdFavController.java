package com.servPet.pdFav.controller;

import javax.servlet.http.HttpServletRequest;
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

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import com.servPet.pdFav.model.PdFavService;
import com.servPet.pdFav.model.PdFavVO;

@Controller
@RequestMapping("/pdFav")
public class PdFavController {

    @Autowired
    private PdFavService pdFavService;

    // 會員新增收藏商品
    @GetMapping("addPdFav")
    public String addPdFav(ModelMap model) {
        PdFavVO pdFavVO = new PdFavVO();
        model.addAttribute("pdFavVO", pdFavVO);
        return "back-end/pdFav/addPdFav";
    }

    // 新增或更新收藏商品 (表單提交)
    @PostMapping("insert")
    public String insert(@Valid PdFavVO pdFavVO, BindingResult result, ModelMap model) {
        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
        if (result.hasErrors()) {
            return "back-end/pdFav/addPdFav";
        }
        /*************************** 2.開始新增資料 *****************************************/
        pdFavService.addFavorite(pdFavVO);
        /*************************** 3.新增完成,準備轉交(Send the Success view) **************/
        List<PdFavVO> list = pdFavService.getAllFavoritesByMebId(pdFavVO.getMebVO().getMebId());
        model.addAttribute("pdFavListData", list);
        model.addAttribute("success", "- (新增成功)");
        return "redirect:/pdFav/listAllPdFav";
    }

    // 查詢特定會員的所有收藏商品
    @PostMapping("getOne_For_Update")
    public String getOne_For_Update(@RequestParam("pdFavId") String pdFavId, ModelMap model) {
        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
        /*************************** 2.開始查詢資料 *****************************************/
        PdFavVO pdFavVO = pdFavService.getFavoriteById(Integer.valueOf(pdFavId));
        /*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
        model.addAttribute("pdFavVO", pdFavVO);
        return "back-end/pdFav/update_pdFav_input";
    }

    // 更新收藏商品 (表單提交)
    @PostMapping("update")
    public String update(@Valid PdFavVO pdFavVO, BindingResult result, ModelMap model) {
        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
        if (result.hasErrors()) {
            return "back-end/pdFav/update_pdFav_input";
        }
        /*************************** 2.開始修改資料 *****************************************/
        pdFavService.addFavorite(pdFavVO);
        /*************************** 3.修改完成,準備轉交(Send the Success view) **************/
        model.addAttribute("success", "- (修改成功)");
        pdFavVO = pdFavService.getFavoriteById(pdFavVO.getPdFavId());
        model.addAttribute("pdFavVO", pdFavVO);
        return "back-end/pdFav/listOnePdFav";
    }

    // 刪除收藏商品
    @PostMapping("delete")
    public String delete(@RequestParam("pdFavId") String pdFavId, ModelMap model) {
        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
        /*************************** 2.開始刪除資料 *****************************************/
        pdFavService.deleteFavorite(Integer.valueOf(pdFavId));
        /*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
        model.addAttribute("success", "- (刪除成功)");
        return "redirect:/pdFav/listAllPdFav";
    }

    // 使用者查看自己的收藏列表
    @PostMapping("listPdFavs_ByCompositeQuery")
    public String listAllPdFav(HttpServletRequest req, Model model) {
        Map<String, String[]> map = req.getParameterMap();
        List<PdFavVO> list = pdFavService.getAllFavorites(map);
        model.addAttribute("pdFavListData", list);
        return "back-end/pdFav/listAllPdFav";
    }
    
    @GetMapping("listFavorites")
    @ResponseBody
    public List<Map<String, Object>> listFavorites(@RequestParam("mebId") Integer mebId) {
        List<PdFavVO> favorites = pdFavService.getAllFavoritesByMebId(mebId);
        List<Map<String, Object>> result = favorites.stream().map(fav -> {
            Map<String, Object> map = new HashMap<>();
            map.put("pdFavId", fav.getPdFavId());
            map.put("productName", fav.getPdDetailsVO().getPdName());  // 假設 PdDetailsVO 有 pdName 欄位
            map.put("pdFavStatus", fav.getPdFavStatus());
            return map;
        }).collect(Collectors.toList());
        return result;
    }

} 
