package com.servPet.mebPdo.controller;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.servPet.meb.model.MebService;
import com.servPet.meb.model.MebVO;
import com.servPet.mebPdo.model.MebPdoService;
import com.servPet.pdo.model.PdoVO;
import com.servPet.pdoItem.model.PdoItemVO;

@Controller
@RequestMapping("/mebPdo")
public class MebPdoController {

    @Autowired
    private MebPdoService mebPdoService;

    @Autowired
    private MebService mebService;
    
    // 取得會員所有訂單
    @GetMapping("/mebPdo")
    public String getAllOrders(Model model,Principal principal) {
        String email = principal.getName();
        Optional<MebVO> memberOptional = mebService.findMemberByEmail(email);
        MebVO mebVO = memberOptional.get();
        Integer mebId = mebVO.getMebId();
        System.out.println("收到的 mebId: " + mebId); // 輸出收到的 mebId
        if (mebId == null) {
            throw new RuntimeException("mebId 不能為空");
        }
        List<PdoVO> mebPdo = mebPdoService.getAllOrdersByMebId(mebId);
        model.addAttribute("mebPdo", mebPdo);
        return "front_end/mebpdo/mebPdo"; // 對應到 templates/front_end/mebPdo/mebpdo.html
    }
    
    // 會員編號 TEST
//    @GetMapping("/mebPdo")
//    public String getAllOrders(Model model) {
//        Integer mebId = 2011; // 將這裡替換為資料庫中已存在的會員編號
//        List<PdoVO> mebPdo = mebPdoService.getAllOrdersByMebId(mebId);
//        model.addAttribute("mebPdo", mebPdo);
//        return "front_end/mebpdo/mebPdo"; // 返回對應的 Thymeleaf 頁面
//    }

    // 查看單一訂單詳情
//    @GetMapping("/mebPdo/{pdoId}")
//    public String getOrderDetails(Model model, @PathVariable Integer pdoId) {
//        PdoVO oneMebpdo = mebPdoService.getOrderById(pdoId);                 // 從 MebPdoService 取得訂單資料
//        List<PdoItemVO> pdoItems = mebPdoService.getPdoItemsByPdoId(pdoId);  // 查詢商品訂單明細
//        model.addAttribute("oneMebpdo", oneMebpdo);                          // 將訂單資料傳到 Thymeleaf
//        model.addAttribute("pdoItems", pdoItems);
//        return "front_end/mebpdo/listOneMebPdo";                            // 返回對應的頁面顯示訂單明細
//    }
    
    @GetMapping("/mebPdo/{pdoId}")
    public String getOrderDetails(Model model, @PathVariable Integer pdoId) {
        Optional<PdoVO> optionalOrder = mebPdoService.getOrderById(pdoId);

        if (optionalOrder.isPresent()) {
            PdoVO oneMebpdo = optionalOrder.get();
            List<PdoItemVO> pdoItems = mebPdoService.getPdoItemsByPdoId(pdoId);

            model.addAttribute("oneMebpdo", oneMebpdo);
            model.addAttribute("pdoItems", pdoItems);

            return "front_end/mebpdo/listOneMebPdo";
        } else {
            model.addAttribute("errorMessage", "找不到該筆訂單");
            return "errorPage"; // 轉到錯誤頁面或重新導向
        }
    }


    // 取消訂單並金儲值金退回錢包
    @PostMapping("/{pdoId}/cancel")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> cancelOrder(@PathVariable Integer pdoId) {
        Map<String, Object> response = new HashMap<>();
        Optional<PdoVO> orderOptional = mebPdoService.getOrderById(pdoId);

        if (orderOptional.isPresent()) {
            boolean cancelSuccess = mebPdoService.cancelOrder(pdoId);

            if (cancelSuccess) {
                response.put("success", true);
                response.put("message", "訂單已取消，金額已退回錢包");
                return ResponseEntity.ok(response);
            } else {
                response.put("success", false);
                response.put("message", "商品已出貨或非宅配，無法取消訂單");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } else {
            response.put("success", false);
            response.put("message", "找不到訂單");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
    
    // 修改地址
    @PostMapping("/{pdoId}/updateAddress")
    @ResponseBody
    public ResponseEntity<String> updateAddress(@PathVariable Integer pdoId, String newAddress) {
        System.out.println("收到修改地址請求，訂單ID: " + pdoId + ", 新地址: " + newAddress);

        Optional<PdoVO> optionalOrder = mebPdoService.getOrderById(pdoId);

        if (!optionalOrder.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("訂單不存在，請確認訂單資訊！");
        }

        PdoVO order = optionalOrder.get();

        // 檢查狀態條件
        boolean canModifyAddress = "0".equals(order.getPdoStatus())        // 訂單狀態: 進行中
                                && "1".equals(order.getPaymentStatus())    // 付款狀態: 已付款
                                && "0".equals(order.getShippingMethod())   // 配送方式: 宅配
                                && "0".equals(order.getShippingStatus());  // 配送狀態: 理貨中

        if (!canModifyAddress) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("商品已出貨或訂單狀態不符，無法修改配送地址！");
        }

        // 更新地址
        order.setShippingAddr(newAddress);
        order.setPdoUpdateTime(new java.util.Date());
        mebPdoService.saveOrder(order); // 儲存修改後的訂單

        return ResponseEntity.ok("配送地址已成功更新");
    }

}
