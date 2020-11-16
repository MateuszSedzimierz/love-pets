package pl.sedzimierz.lovepets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.sedzimierz.lovepets.controller.editor.PetTypeEditor;
import pl.sedzimierz.lovepets.controller.editor.UserEditor;
import pl.sedzimierz.lovepets.security.SecurityUtils;
import pl.sedzimierz.lovepets.service.PetService;
import pl.sedzimierz.lovepets.service.PetTypeService;
import pl.sedzimierz.lovepets.service.UserService;
import pl.sedzimierz.lovepets.service.dto.PetDTO;
import pl.sedzimierz.lovepets.service.dto.PetTypeDTO;
import pl.sedzimierz.lovepets.service.dto.UserDTO;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;
    private final UserService userService;
    private final PetTypeService petTypeService;

    public PetController(PetService petService, UserService userService, PetTypeService petTypeService) {
        this.petService = petService;
        this.userService = userService;
        this.petTypeService = petTypeService;
    }

    @GetMapping("/{petId}/details")
    public String getPetDetails(@PathVariable Long petId, Model model) {
        return petService.getPetById(petId)
                .map(petDTO -> {
                    model.addAttribute("petDTO", petDTO);
                    return "petDetails";
                }).orElse("notFound");
    }

    @GetMapping("/new")
    public String getPetCreateForm(Model model) {
        PetDTO petDTO = new PetDTO();
        SecurityUtils.getCurrentUserLogin()
                .flatMap(userService::getUserByLogin)
                .ifPresent(user -> {
                    petDTO.setOwnerDTO(user);
                    petDTO.setAddressDTO(user.getAddressDTO().clone());
                });

        model.addAttribute("petDTO", petDTO);
        model.addAttribute("petTypes", petTypeService.getAllPetTypes());
        return "petForm";
    }

    @PostMapping("/save")
    public String savePet(@Valid PetDTO petDTO, @RequestParam MultipartFile petImage) {
        Optional<PetDTO> savedPet = petService.savePet(petDTO, petImage);
        return savedPet
                .map(pet -> "redirect:/pets/" + pet.getId() + "/details")
                .orElse("redirect:/pets/new");
    }

    @GetMapping("/{petId}/edit")
    public String getPetUpdateForm(@PathVariable Long petId, Model model) {
        Optional<PetDTO> existingPet = petService.getPetById(petId);
        if (existingPet.isPresent()) {
            PetDTO pet = existingPet.get();
            model.addAttribute("petDTO", pet);
            model.addAttribute("petTypes", petTypeService.getAllPetTypes());
            return "petForm";
        }
        return "notFound";
    }

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(PetTypeDTO.class, new PetTypeEditor(petTypeService));
        webDataBinder.registerCustomEditor(UserDTO.class, new UserEditor(userService));
    }
}
