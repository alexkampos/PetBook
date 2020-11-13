package com.Project.PetBook.controllers;

import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Services.MyUserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Welcome {

    @Autowired
    MyUserServiceInterface myUserServiceInterface;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {

        return "sign-in-form.html";
    }

    @GetMapping("/")
    public String welcome() {
        return "welcome.html";
    }

    @PostMapping("/home")
    public String home() {
        return "home.html";
    }

    @GetMapping("/home")
    public String gethome() {
        return "home.html";
    }

    @GetMapping("/registerUser")
    public String showForm(ModelMap mm) {
        mm.addAttribute("newUser", new MyUser());
        return "register.html";
    }

    @PostMapping("/registerUser")
    public String submitForm(@ModelAttribute MyUser myUser) {

        myUser.setUserPassword(passwordEncoder.encode(myUser.getUserPassword()));

        myUserServiceInterface.insertUser(myUser);
        return "welcome";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "sign-in-form.html";
    }
}
