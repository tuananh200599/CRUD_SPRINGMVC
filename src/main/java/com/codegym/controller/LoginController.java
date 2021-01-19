package com.codegym.controller;

import com.codegym.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class LoginController {
    @ModelAttribute("user")
    public User setUpUserForm() { return new User(); }
    @GetMapping("/login")
    public String Index() { return "index";}
    @PostMapping("/dologin")
    public String doLogin(@ModelAttribute("user") User user, Model model) {
        if (user.getEmail().equals("vinh.tran@codegym.com") &&
                user.getPassword().equals("12345")) {
            model.addAttribute("message", "Login success. Welcome: ");
        } else {
            model.addAttribute("message", "Login failed. Try again.");
        }
        return "index";
    }
}
