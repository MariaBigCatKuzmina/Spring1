package ru.kuzmina.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.model.Dto.UserDto;
import ru.kuzmina.exceptions.ApplicationError;
import ru.kuzmina.model.User;
import ru.kuzmina.services.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String listPage(@RequestParam(required = false) String usernameFilter,
                           @RequestParam(required = false) String emailFilter,
                           Model model) {

//        model.addAttribute("users", userService.userByFilter(usernameFilter, emailFilter));
        model.addAttribute("users", userService.findUsersByFilter(usernameFilter, emailFilter));
        return "user";
    }


    @GetMapping("/{id}")
    public String form(@PathVariable("id") Long id, Model model) {
        Optional<UserDto> user = userService.findById(id);
        if (user.isPresent()) {
            model.addAttribute("user", user.get());
            return "user_form";
        }
        model.addAttribute("error", new ApplicationError(404,"User not found"));
        return "error";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        User user = new User("");
        model.addAttribute("user", user);
        return "user_form";
    }

    @PostMapping
    public String saveUser(@Valid @ModelAttribute("user") UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        }
        userService.save(userDto);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String dropUser(@PathVariable Long id) {
        userService.deleteById(id);
        return "redirect:/user";
    }
}
