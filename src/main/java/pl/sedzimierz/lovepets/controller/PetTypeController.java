package pl.sedzimierz.lovepets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String createNewPetType(@Valid PetTypeDTO petTypeDTO, RedirectAttributes redirectAttributes) {
        try {
            petTypeService.createPetType(petTypeDTO);
            redirectAttributes.addFlashAttribute("successMessage", "Created new pet type!");
        } catch (PetTypeNameAlreadyExistsException exc) {
            redirectAttributes.addFlashAttribute("errorMessage", exc.getMessage());
        }
        return "redirect:/pet-types";
    }
}
