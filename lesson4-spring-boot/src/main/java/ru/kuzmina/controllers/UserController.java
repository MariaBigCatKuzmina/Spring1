package ru.kuzmina.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kuzmina.persist.User;
import ru.kuzmina.persist.UserRepository;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping
    public String listPage(Model model) {
        model.addAttribute("users", userRepository.getAll());
        return "user";
    }

    @GetMapping("/{id}")
    public String form(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userRepository.getById(id));
        return "user_form";
    }

    @GetMapping("/add")
    public String addUser(Model model) {
        User user = new User("");
        userRepository.insert(user);
        model.addAttribute("user", user);
        return "user_form";
    }

    @PostMapping
    public String saveUser(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user_form";
        }
        userRepository.update(user);
        return "redirect:user";
    }

    @DeleteMapping("/{id}")
    public String dropUser(@PathVariable Long id){
        userRepository.dropById(id);
        return "redirect:/user";
    }
}
