package com.zmeevsky.springmvc.controller;

import com.zmeevsky.springmvc.entity.User;
import com.zmeevsky.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @GetMapping("/user")
    public String getUserPage(Principal principal, Model model) {

//        User user = null;
//
//        if (principal instanceof User) {
//            user = (User) principal;
//        }

        String userName = principal.getName();

        User user = userService.findByUsername(userName);

        model.addAttribute("user", user);

        return "user-page";
    }

    @GetMapping("/admin")
    public String getAdminPage(Principal principal, Model model) {

        String userName = principal.getName();

        User user = userService.findByUsername(userName);
        List<User> users = userService.getUsers();

        model.addAttribute("userList", users);
        model.addAttribute("user", user);

        return "admin-page";
    }

    @GetMapping("/show-form-for-add")
    public String showFormForAdd(Model model) {

        model.addAttribute("user", new User());

        return "user-form";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {

        userService.saveUser(user);

        return "redirect:/users/admin";
    }

    @GetMapping("/show-form-for-update")
    public String showFormForUpdate(@RequestParam("userId") int id, Model model) {

        User user = userService.getUser(id);

        model.addAttribute("user", user);

        return "update-form";
    }

    @PostMapping("/update-user")
    public String updateUser(@ModelAttribute("user") User user) {

        userService.updateUser(user);

        return "redirect:/users/admin";
    }

    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int id) {

        userService.deleteUser(id);

        return "redirect:/users/admin";
    }

    @GetMapping("/search")
    public String searchUsers(@RequestParam("theSearchName") String theSearchName, Model model) {

        List<User> users = userService.searchUsers(theSearchName);

        model.addAttribute("users", users);

        return "users";
    }
}
