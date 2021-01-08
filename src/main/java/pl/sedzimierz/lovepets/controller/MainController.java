package pl.sedzimierz.lovepets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sedzimierz.lovepets.service.PetService;

@Controller
@RequestMapping("/")
public class MainController {

    private final PetService petService;

    public MainController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping
    public String getIndexPage(Model model) {
        model.addAttribute("latestPets", petService.getThreeLatestNotAdoptedPets());
        model.addAttribute("adopted", petService.getThreeRecentlyAdoptedPets());
        return "index";
    }

    @GetMapping("/about")
    public String getAboutPage() {
        return "about";
    }

    @GetMapping("/support")
    public String getSupportPage() {
        return "support";
    }
}
