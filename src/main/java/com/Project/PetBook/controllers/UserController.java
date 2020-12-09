package com.Project.PetBook.controllers;

import com.Project.PetBook.Services.MyUserService;
import com.Project.PetBook.Utils.UtilMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UserController {

    @Autowired
    MyUserService myUserService;

    @Autowired
    private UtilMethods utilMethods;

    
    @GetMapping("profile-page")
    public String getProfilePage(Model model) {

        model.addAttribute("MyUser", utilMethods.getLoggedInUser());
        model.addAttribute("userImages", utilMethods.getLoggedInUser().getUsersImages());

        return "profile/profile-page";
    }

    
    @PostMapping("/upload-profile-image")
    public String uploadImage(@RequestParam("file") MultipartFile file) {

        try {
            myUserService.saveProfileImage(file);
        } catch (Exception ex) {
            System.out.println(ex);
        }
        

        return "redirect:/home-page";
    }



}
