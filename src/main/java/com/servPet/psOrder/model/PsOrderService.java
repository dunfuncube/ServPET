package com.servPet.psOrder.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("PsOrderService")
public class PsOrderService {

    @Autowired
    private PsOrderRepository repository;

    // 新增
    public void addPs(PsOrderVO psOrderVO) {
        repository.save(psOrderVO);
    }

    // 修改
    public void update(PsOrderVO psOrderVO) {
        repository.save(psOrderVO);
    }

    // 刪除
    public void deletePsOrder(Integer psoId) {
        if (repository.existsById(psoId))
            repository.deleteById(psoId);
    }

    // 查詢所有訂單
    public List<PsOrderVO> getAll() {
        return repository.findAll();
    }

    // 根據美容師編號查詢訂單
    public PsOrderVO getOnePsOrder(Integer psId) { // 可更安全清晰的處理返回值為空的情況
        Optional<PsOrderVO> optional = repository.findById(psId);
//   return optional.get();
        return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
    }

    // 根據會員編號查詢訂單
    public PsOrderVO getOneMebOrder(Integer mebId) { // 可更安全清晰的處理返回值為空的情況
        Optional<PsOrderVO> optional = repository.findById(mebId);
//     return optional.get();
        return optional.orElse(null); // public T orElse(T other) : 如果值存在就回傳其值，否則回傳other的值
    }

    // 根據訂單狀態查詢訂單
    public List<PsOrderVO> getStatusOrder(String bookingStatus) {
        return repository.findByBookingStatus(bookingStatus);

    }
}