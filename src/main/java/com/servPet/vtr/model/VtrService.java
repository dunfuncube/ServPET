package com.servPet.vtr.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VtrService {

	@Autowired
	private VtrRepository vtrRepository;

	// 获取所有的 VtrVO
	public List<VtrVO> getAllVtrVOs() {
		return vtrRepository.findAll();
	}

	// 根据 ID 获取 VtrVO
	public Optional<VtrVO> getVtrVOById(Integer id) {
		return vtrRepository.findById(id);
	}

	// 保存 VtrVO
	public void saveVtrVO(VtrVO vtrVO) {
		vtrRepository.save(vtrVO);
	}

	// 删除 VtrVO
	public void deleteVtrVO(Integer id) {
		vtrRepository.deleteById(id);
	}
}
