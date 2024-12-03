package com.servPet.meb.model;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MebRepository extends JpaRepository<MebVO, Integer> {
	Optional<MebVO> findByMebMail(String mebMail); // 根據電子郵件查找會員

	Optional<MebVO> findByMebPhone(String mebPhone);
}
