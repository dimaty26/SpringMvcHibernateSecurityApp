package com.zmeevsky.springmvc.controller;

import com.zmeevsky.springmvc.entity.User;
import com.zmeevsky.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public String getUsers(Model model) {

        List<User> users = userService.getUsers();

        model.addAttribute("userList", users);

        return "users";
    }

    @GetMapping("/show-form-for-add")
    public String showFormForAdd(Model model) {

        User user = new User();
        model.addAttribute("user", user);

        return "user-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);

        return "redirect:/users/list";
    }

    @GetMapping("/show-form-for-update/{id}")
    public String updateUser(@PathVariable("id") int id, Model model) {

        User user = userService.getUser(id);

        model.addAttribute("user", user);

        return "user-form";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int id) {

        userService.deleteUser(id);

        return "redirect:/users/list";
    }

    @GetMapping("/search")
    public String searchUsers(@RequestParam("theSearchName") String theSearchName, Model model) {

        List<User> users = userService.searchUsers(theSearchName);

        model.addAttribute("users", users);

        return "users";
    }
}
