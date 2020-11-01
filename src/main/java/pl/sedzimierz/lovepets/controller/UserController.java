package pl.sedzimierz.lovepets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.sedzimierz.lovepets.service.UserService;
import pl.sedzimierz.lovepets.service.dto.UserDTO;
import pl.sedzimierz.lovepets.service.exception.EmailAlreadyUsedException;
import pl.sedzimierz.lovepets.service.exception.LoginAlreadyUsedException;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public String createNewUser(UserDTO userDTO, String password, Model model) {
        try {
            userService.createUser(userDTO, password);
            model.addAttribute("registrationSuccessful", true);
            return "login";
        } catch (LoginAlreadyUsedException | EmailAlreadyUsedException exc) {
            model.addAttribute("registrationError", exc.getMessage());
            return "userRegistration";
        }
    }
}
