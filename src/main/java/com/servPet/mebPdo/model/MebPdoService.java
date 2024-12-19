package com.servPet.mebPdo.model;

import com.servPet.pdo.model.PdoVO;
import com.servPet.pdoItem.model.PdoItemRepository;
import com.servPet.pdoItem.model.PdoItemVO;
import com.servPet.vtr.model.VtrService;
import com.servPet.pdo.model.PdoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
public class MebPdoService {

    @Autowired
    private PdoRepository pdoRepository;

    @Autowired
    private PdoItemRepository pdoItemRepository;

    @Autowired
    private VtrService vtrService;

    // 取得會員的所有訂單
    public List<PdoVO> getAllOrdersByMebId(Integer mebId) {
        return pdoRepository.findAll().stream()
                .filter(pdo -> pdo.getMebVO() != null && pdo.getMebVO().getMebId().equals(mebId))
                .toList();
    }

    // 取得單一訂單資訊
    public Optional<PdoVO> getOrderById(Integer pdoId) {
        return pdoRepository.findById(pdoId);
    }

    // 取得指定訂單的詳細資訊
    public List<PdoItemVO> getPdoItemsByPdoId(Integer pdoId) {
        return pdoItemRepository.findByPdoVO(pdoId);
    }

    // 取消訂單並金額退回錢包（加上限制條件）
    @ResponseBody
    public boolean cancelOrder(Integer pdoId) {
        Optional<PdoVO> optionalOrder = pdoRepository.findById(pdoId);

        if (optionalOrder.isPresent()) {
            PdoVO order = optionalOrder.get();

            // 取消條件驗證
            boolean canCancel = "0".equals(order.getPdoStatus()) &&    // 進行中
                                "1".equals(order.getPaymentStatus()) && // 已付款
                                "0".equals(order.getShippingMethod()) &&// 宅配
                                "0".equals(order.getShippingStatus());  // 理貨中

            if (canCancel) {
                // 修改訂單狀態
                order.setPdoStatus("2");          // 訂單狀態設為已取消
                order.setPaymentStatus("2");      // 付款狀態設為已退款
                order.setPdoUpdateTime(new java.util.Date());
                pdoRepository.save(order);

                // 退款邏輯
                Integer refundAmount = order.getPdTotalPrice();
                Integer mebId = order.getMebVO().getMebId();
                vtrService.createTransaction(mebId, refundAmount, "退款");

                return true; // 取消成功
            }
        }
        return false; // 無法取消
    }
    
    // 修改地址
    public boolean updateShippingAddress(Integer pdoId, String newAddress) {
        // 查詢訂單
        Optional<PdoVO> optionalOrder = pdoRepository.findById(pdoId);
        if (!optionalOrder.isPresent()) {
            throw new RuntimeException("訂單不存在");
        }

        PdoVO pdo = optionalOrder.get();

        // 檢查訂單狀態，只能在「進行中」且「理貨中」時修改地址
        if (!"0".equals(pdo.getPdoStatus()) || !"0".equals(pdo.getShippingStatus()) ) {
            return false; // 不允許修改地址
        }

        // 更新地址
        pdo.setShippingAddr(newAddress);
        pdo.setPdoUpdateTime(new java.util.Date()); // 更新修改時間
        pdoRepository.save(pdo);

        return true;
    }
    public void saveOrder(PdoVO order) {
        pdoRepository.save(order);
    }


}
