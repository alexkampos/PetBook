package com.Project.PetBook.controllers;

import com.Project.PetBook.Dto.PasswordForgotDto;
import com.Project.PetBook.Models.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.Project.PetBook.Services.MyUserService;

@Controller
@RequestMapping("/")
public class Welcome {

    @Autowired
    private MyUserService myUserServiceInterface;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @GetMapping("/login")
    public String login(ModelMap mm) {
        mm.addAttribute("passwordResetDto", new PasswordForgotDto());
        return "authentication/sign-in-form";
    }

    @GetMapping("/")
    public String welcome() {
        return "home/welcome";
    }

    @GetMapping("/home")
    public String showHomeFromGet() {
        return "home/home";
    }
    
    @PostMapping("/home")
    public String showHomeFromPost() {
        return "home/home";
    }

    @GetMapping("/register-user")
    public String showForm(ModelMap mm) {
        mm.addAttribute("newUser", new MyUser());
        return "registration/register";
    }

    @PostMapping("/register-user")
    public String submitForm(@ModelAttribute MyUser myUser) {

        myUser.setUserPassword(passwordEncoder.encode(myUser.getUserPassword()));

        myUserServiceInterface.insertUser(myUser);
        return "home/welcome";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "authentication/sign-in-form";
    }
}
