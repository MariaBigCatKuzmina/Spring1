package ru.kuzmina.controllers;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.exceptions.ResourceNotFoundException;
import ru.kuzmina.model.Dto.UserDto;
import ru.kuzmina.exceptions.ApplicationError;
import ru.kuzmina.model.User;
import ru.kuzmina.services.RoleService;
import ru.kuzmina.services.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping
    public String listPage(@RequestParam(required = false) String usernameFilter,
                           @RequestParam(required = false) String emailFilter,
                           Model model) {
        model.addAttribute("users", userService.findUsersByFilter(usernameFilter, emailFilter));
        return "user";
    }


    @GetMapping("/{id}")
    public String form(@PathVariable("id") Long id, Model model) {
//        Optional<UserDto> user = userService.findById(id);
//        if (user.isPresent()) {
            model.addAttribute("roles", roleService.findAll());
//            model.addAttribute("user", user.get());
            model.addAttribute("user", userService.findById(id)
                    .orElseThrow(()->new ResourceNotFoundException("User not found: " + id)));
            return "user_form";
//        }
//        model.addAttribute("error", new ApplicationError(404,"User not found"));
//        return "error";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        User user = new User("");
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("user", user);
        return "user_form";
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    @PostMapping
    public String saveUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        }
        userService.save(userDto);
        return "redirect:/user";
    }

    @Secured({"ROLE_ADMIN", "ROLE_SUPER_ADMIN"})
    @DeleteMapping("/{id}")
    public String dropUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/user";
    }
}
