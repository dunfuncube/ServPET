package com.servPet.psSvc.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PsSvcRepository extends JpaRepository<PsSvcVO, Integer> {

//    //根據服務項目查詢保母
//    @Query(value = "from PsSvcVO where psVO.psId=?1 order by psSvcItemVO.svcId")
//    List<PsSvcVO> findBySvcId(int svcId);

    //根據保母查詢服務項目?
//    @Query(value = "from PsSvcVO where psSvcVO.psId=?1 order by psSvcItemVO.svcId")
//    List<PsSvcVO> findBySvcId(int svcId);


}
