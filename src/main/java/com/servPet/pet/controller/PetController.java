package com.servPet.pet.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.servPet.pet.model.PetService;
import com.servPet.pet.model.PetVO;

@Controller
@RequestMapping("/pets")
public class PetController {

	@Autowired
	private PetService petService;

	// Display list of all pets on the homepage
	@GetMapping
	public String getAllPets(Model model) {
		List<PetVO> pets = petService.getAllPets();
		model.addAttribute("pets", pets); // Add pets list to model
		return "pets"; // Thymeleaf template to render
	}

	// Display a single pet by ID
	@GetMapping("/{id}")
	public String getPetById(@PathVariable Long id, Model model) {
		Optional<PetVO> pet = petService.getPetById(id);
		if (pet.isPresent()) {
			model.addAttribute("pet", pet.get());
			return "petDetail"; // Thymeleaf template to show details of a pet
		} else {
			return "error"; // Return error template if pet not found
		}
	}

	// Create a new pet and save it
	@PostMapping
	public String createPet(@ModelAttribute PetVO petVO) {
		petService.savePet(petVO);
		return "redirect:/pets"; // Redirect to the list of pets after saving
	}

	// Delete a pet by ID
	@DeleteMapping("/{id}")
	public String deletePet(@PathVariable Long id) {
		petService.deletePet(id);
		return "redirect:/pets"; // Redirect to the list of pets after deletion
	}
}
