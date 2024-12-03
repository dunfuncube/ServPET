package com.servPet.repository;

import com.servPet.model.NtfyVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NtfyRepository extends JpaRepository<NtfyVO, Integer> {
    
    // 查找某個會員的所有通知（按日期降序排序）
    List<NtfyVO> findByMebVO_MebIdOrderByDateDesc(Integer mebId);
    
    // 查找某個會員的特定狀態的通知（用於查詢未讀通知）
    List<NtfyVO> findByMebVO_MebIdAndStatusOrderByDateDesc(Integer mebId, Integer status);
    
    // 查找所有通知（按日期降序排序）
    List<NtfyVO> findAllByOrderByDateDesc();
}

