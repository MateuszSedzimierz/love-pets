package pl.sedzimierz.lovepets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.sedzimierz.lovepets.service.dto.UserDTO;

@Controller
public class AccountController {

    @GetMapping("/register")
    public String getNewUserForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        model.addAttribute("password", "");
        return "userRegistration";
    }
}
