package com.servPet.psSvc.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("PsSvcService")
//保母個人的服務清單(讓會員可以依照保母編號、服務項目、價格來單筆或複合查詢)
public class PsSvcService {
    @Autowired
    private PsSvcRepository psSvcRepository;

    // 根據服務項目 ID 查詢保母清單
//    public List<PsSvcVO> findBySvcId(int svcId) {
//        return psSvcRepository.findBySvcId(svcId);
//    }

}
