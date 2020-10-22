package com.zmeevsky.springmvc.controller;

import com.zmeevsky.springmvc.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
public class StartPageController {

    @GetMapping("/")
    public String showStartPage(Model model, Principal principal) {

//        User loginUser = (User) ((Authentication) principal).getPrincipal();
//
//        model.addAttribute("username", loginUser.getUsername());
//        model.addAttribute("roles", loginUser.getAuthorities());

        return "start-page";
    }
}
