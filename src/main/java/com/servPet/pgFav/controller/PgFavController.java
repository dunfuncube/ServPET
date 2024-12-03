package com.servPet.pgFav.controller;

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

import com.servPet.pgFav.model.PgFavService;
import com.servPet.pgFav.model.PgFavVO;

@Controller
@RequestMapping("/pdFav")
public class PgFavController {

    @Autowired
    PgFavService pgFavService;

    // 會員新增收藏商品
    @GetMapping("addPgFav")
    public String addPgFav(ModelMap model) {
        PgFavVO pgFavVO = new PgFavVO();
        model.addAttribute("pgFavVO", pgFavVO);
        return "back-end/pgFav/addPgFav";
    }

    // 新增或更新收藏商品 (表單提交)
    @PostMapping("insert")
    public String insert(@Valid PgFavVO pgFavVO, BindingResult result, ModelMap model) {
        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
        if (result.hasErrors()) {
            return "back-end/pgFav/addPgFav";
        }
        /*************************** 2.開始新增資料 *****************************************/
        pgFavService.addFavorite(pgFavVO);
        /*************************** 3.新增完成,準備轉交(Send the Success view) **************/
        List<PgFavVO> list = pgFavService.getAllFavoritesByMebId(pgFavVO.getMebVO().getMebId());
        model.addAttribute("pgFavListData", list);
        model.addAttribute("success", "- (新增成功)");
        return "redirect:/pgFav/listAllPgFav";
    }

    // 查詢特定會員的所有收藏商品
    @PostMapping("getOne_For_Update")
    public String getOne_For_Update(@RequestParam("pgFavId") String pgFavId, ModelMap model) {
        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
        /*************************** 2.開始查詢資料 *****************************************/
        PgFavVO pgFavVO = pgFavService.getFavoriteById(Integer.valueOf(pgFavId));
        /*************************** 3.查詢完成,準備轉交(Send the Success view) **************/
        model.addAttribute("pgFavVO", pgFavVO);
        return "back-end/pgFav/update_pgFav_input";
    }

//    // 更新收藏商品 (表單提交)
//    @PostMapping("update")
//    public String update(@Valid PgFavVO pgFavVO, BindingResult result, ModelMap model) {
//        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
//        if (result.hasErrors()) {
//            return "back-end/pgFav/update_pgFav_input";
//        }
//        /*************************** 2.開始修改資料 *****************************************/
//        pgFavService.addFavorite(pgFavVO);
//        /*************************** 3.修改完成,準備轉交(Send the Success view) **************/
//        model.addAttribute("success", "- (修改成功)");
//        pgFavVO = pgFavService.getFavoriteById(pgFavVO.getPgFavId());
//        model.addAttribute("pgFavVO", pgFavVO);
//        return "back-end/pgFav/listOnePgFav";
//    }

    // 刪除收藏商品
    @PostMapping("delete")
    public String delete(@RequestParam("pgFavId") String pgFavId, ModelMap model) {
        /*************************** 1.接收請求參數 - 輸入格式的錯誤處理 ************************/
        /*************************** 2.開始刪除資料 *****************************************/
        pgFavService.deleteFavorite(Integer.valueOf(pgFavId));
        /*************************** 3.刪除完成,準備轉交(Send the Success view) **************/
        model.addAttribute("success", "- (刪除成功)");
        return "redirect:/pgFav/listAllPgFav";
    }

//    // 使用者查看自己的收藏列表
//    @PostMapping("listPgFavs_ByCompositeQuery")
//    public String listAllPgFav(HttpServletRequest req, Model model) {
//        Map<String, String[]> map = req.getParameterMap();
//        List<PgFavVO> list = pgFavService.getAllFavorites(map);
//        model.addAttribute("pgFavListData", list);
//        return "back-end/pgFav/listAllPgFav";
//    }
    
    @GetMapping("listFavorites")
    @ResponseBody
    public List<Map<String, Object>> listFavorites(@RequestParam("mebId") Integer mebId) {
        List<PgFavVO> favorites = pgFavService.getAllFavoritesByMebId(mebId);
        List<Map<String, Object>> result = favorites.stream().map(fav -> {
            Map<String, Object> map = new HashMap<>();
            map.put("pgFavId", fav.getPgFavId());
//            map.put("productName", fav.getPgFavVO().getPdName());  // 假設 PgFavVO 有 pdName 欄位
            map.put("pdFavStatus", fav.getPgFavStatus());
            return map;
        }).collect(Collectors.toList());
        return result;
    }

} 
