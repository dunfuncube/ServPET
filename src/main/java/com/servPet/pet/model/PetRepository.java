package com.servPet.pet.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends JpaRepository<PetVO, Long> {
	// 您可以在這裡定義額外的查詢方法，例如：
	// List<PetVO> findByPetName(String petName);
}
