
package com.Project.PetBook.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Welcome {
    
    @GetMapping("/login")
    public String login(){
    
    return "sign-in-form.html";
    }
    
    @GetMapping("/")
    public String home(){
    return "home.html";
    }
    
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "sign-in-form.html";
    }
}
