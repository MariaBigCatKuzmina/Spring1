package ru.kuzmina.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.model.User;
import ru.kuzmina.repositories.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public String listPage(@RequestParam(required = false) String usernameFilter,
                           @RequestParam(required = false) String emailFilter,
                           Model model) {

        usernameFilter = usernameFilter == null || usernameFilter.isBlank() ? null : '%' + usernameFilter.trim() + '%';
        emailFilter = emailFilter == null || emailFilter.isBlank() ? null : '%' + emailFilter.trim() + '%';
        model.addAttribute("users", userRepository.userByUsernameAndEmail(usernameFilter, emailFilter));
        return "user";
    }

//    @GetMapping
//    public String listPage(@RequestParam(required = false) String usernameFilter, Model model) {
//        usernameFilter = usernameFilter == null || usernameFilter.isBlank() ? null : '%' + usernameFilter.trim() + '%';
//        model.addAttribute("users", userRepository.userByUsername(usernameFilter));
//        return "user";
//    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userRepository.findById(id).get());
        return "user_form";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        User user = new User("");
        model.addAttribute("user", user);
        return "user_form";
    }

    @PostMapping
    public String saveUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        }
        userRepository.save(user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String dropUser(@PathVariable Long id) {
        userRepository.deleteById(id);
        return "redirect:/user";
    }
}
