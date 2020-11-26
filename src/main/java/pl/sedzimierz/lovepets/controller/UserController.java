package pl.sedzimierz.lovepets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sedzimierz.lovepets.security.AuthoritiesConstants;
import pl.sedzimierz.lovepets.security.SecurityUtils;
import pl.sedzimierz.lovepets.service.UserService;
import pl.sedzimierz.lovepets.service.dto.UserDTO;
import pl.sedzimierz.lovepets.service.exception.EmailAlreadyUsedException;
import pl.sedzimierz.lovepets.service.exception.LoginAlreadyUsedException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/new")
    public String createNewUser(@Valid UserDTO userDTO, String password, Model model) {
        try {
            userService.createUser(userDTO, password);
            model.addAttribute("registrationSuccessful", true);
            return "login";
        } catch (LoginAlreadyUsedException | EmailAlreadyUsedException exc) {
            model.addAttribute("registrationError", exc.getMessage());
            return "userRegistration";
        }
    }

    @GetMapping("/profile")
    public String getUserProfile(Model model) {
        return SecurityUtils
                .getCurrentUserLogin()
                .flatMap(userService::getUserByLogin)
                .map(user -> {
                    model.addAttribute("user", user);
                    return "profile";
                })
                .orElse("notFound");
    }

    @PostMapping("/update")
    public String updateUser(@Valid UserDTO userDTO, RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (!hasUserPermissionToUpdate(userDTO.getLogin())) {
            return "accessDenied";
        }

        try {
            userService.updateUser(userDTO);
            redirectAttributes.addFlashAttribute("updateSuccess", "Update successful!");
        } catch (EmailAlreadyUsedException exc) {
            redirectAttributes.addFlashAttribute("updateError", exc.getMessage());
        }

        String previousPage = request.getHeader("Referer");
        return previousPage.isEmpty() ? "profile" : "redirect:" + previousPage;
    }

    private boolean hasUserPermissionToUpdate(String updatedUserLogin) {
        if (SecurityUtils.hasCurrentUserAuthority(AuthoritiesConstants.ADMIN)) {
            return true;
        }
        return SecurityUtils.getCurrentUserLogin()
                .map(login -> login.equals(updatedUserLogin))
                .orElse(false);
    }
}
