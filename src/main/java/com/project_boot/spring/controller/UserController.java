package com.project_boot.spring.controller;


import com.project_boot.spring.model.User;
import com.project_boot.spring.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
public class UserController {

    private final UserService userService;

    @Autowired()
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String users(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @GetMapping("/new")
    public String addUser(Model model) {
        model.addAttribute("user",new User());

        return "create";
    }

    @PostMapping("/new")
    public String add(@ModelAttribute("user") @Valid User user) {
        userService.addUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.removeUser(id);
        return "redirect:/";
    }

    @GetMapping("edit/{id}")
    public String updateUser(@PathVariable("id") long id, Model model) {
        model.addAttribute(userService.getUserById(id));
        return "edit";
    }

    @PatchMapping("/edit")
    public String update(@Valid User user) {
        userService.updateUser(user);
        return "redirect:/";
    }
}
