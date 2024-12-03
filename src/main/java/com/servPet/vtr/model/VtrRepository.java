package com.servPet.vtr.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VtrRepository extends JpaRepository<VtrVO, Integer> {
	// 可以在這裡添加自訂的查詢方法
}
