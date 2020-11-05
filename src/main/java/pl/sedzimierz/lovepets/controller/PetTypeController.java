package pl.sedzimierz.lovepets.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sedzimierz.lovepets.service.PetTypeService;
import pl.sedzimierz.lovepets.service.dto.PetTypeDTO;
import pl.sedzimierz.lovepets.service.exception.PetTypeNameAlreadyExistsException;

import javax.validation.Valid;

@Controller
@RequestMapping("/pet-types")
public class PetTypeController {

    private final PetTypeService petTypeService;

    public PetTypeController(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }

    @GetMapping
    public String getPetTypesToManagement(Model model) {
        model.addAttribute("petTypes", petTypeService.getAllPetTypes());
        return "petTypesManagement";
    }

    @PostMapping("/new")
    public String createPetType(@Valid PetTypeDTO petTypeDTO, RedirectAttributes redirectAttributes) {
        try {
            petTypeService.createPetType(petTypeDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Created new pet type!");
        } catch (PetTypeNameAlreadyExistsException exc) {
            redirectAttributes.addFlashAttribute("errorMessage", exc.getMessage());
        }
        return "redirect:/pet-types";
    }

    @GetMapping("/{petTypeId}")
    public ResponseEntity<PetTypeDTO> getPetTypeById(@PathVariable Long petTypeId) {
        return petTypeService
                .getPetTypeById(petTypeId)
                .map(petTypeDTO -> new ResponseEntity<>(petTypeDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/edit")
    public String updatePetType(@Valid PetTypeDTO petTypeDTO, RedirectAttributes redirectAttributes) {
        try {
            if (petTypeService.updatePetType(petTypeDTO).isPresent()) {
                redirectAttributes.addFlashAttribute("successMessage", "Updated pet type!");
            } else {
                redirectAttributes.addFlashAttribute("errorMessage", "Incorrect pet type id!");
            }
        } catch (PetTypeNameAlreadyExistsException exc) {
            redirectAttributes.addFlashAttribute("errorMessage", exc.getMessage());
        }
        return "redirect:/pet-types";
    }

    @GetMapping("/{petTypeId}/delete")
    public String deletePetType(@PathVariable Long petTypeId) {
        petTypeService.deletePetType(petTypeId);
        return "redirect:/pet-types";
    }
}
