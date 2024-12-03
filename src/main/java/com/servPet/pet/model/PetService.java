package com.servPet.pet.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {

	@Autowired
	private PetRepository petRepository;

	public List<PetVO> getAllPets() {
		return petRepository.findAll();
	}

	public Optional<PetVO> getPetById(Long id) {
		return petRepository.findById(id);
	}

	public void savePet(PetVO petVO) {
		petRepository.save(petVO);
	}

	public void deletePet(Long id) {
		petRepository.deleteById(id);
	}
}
