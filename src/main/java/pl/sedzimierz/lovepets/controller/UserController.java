package pl.sedzimierz.lovepets.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.sedzimierz.lovepets.security.AuthoritiesConstants;
import pl.sedzimierz.lovepets.security.SecurityUtils;
import pl.sedzimierz.lovepets.service.UserService;
import pl.sedzimierz.lovepets.service.dto.UserDTO;
import pl.sedzimierz.lovepets.service.exception.EmailAlreadyUsedException;
import pl.sedzimierz.lovepets.service.exception.LoginAlreadyUsedException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());
        return "usersManagement";
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId) {
        Optional<UserDTO> existingUser = userService.getUserById(userId);
        return existingUser
                .map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
            redirectAttributes.addFlashAttribute("successMessage", "Update successful!");
        } catch (EmailAlreadyUsedException exc) {
            redirectAttributes.addFlashAttribute("errorMessage", exc.getMessage());
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

    @GetMapping("/{userId}/delete")
    public String deleteUser(@PathVariable Long userId, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUserById(userId);
            redirectAttributes.addFlashAttribute("successMessage", "Deleted user!");
        } catch (Exception exc) {
            redirectAttributes.addFlashAttribute("errorMessage", exc.getMessage());
        }
        return "redirect:/users";
    }

    @GetMapping("/{userId}/admin")
    public String changeAdminAuthority(@PathVariable Long userId, @RequestParam boolean isAdmin) {
        userService.setAdminAuthority(userId, isAdmin);
        return "redirect:/users";
    }
}