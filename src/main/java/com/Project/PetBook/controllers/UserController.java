
package com.Project.PetBook.controllers;

import com.Project.PetBook.Services.MyUserService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class UserController {
    
    @Autowired
    MyUserService myUserService;

    @GetMapping("profile-page")
    public String getProfilePage() {
        
        
        return "profile/profile-page";
        
    }
    
    
    @PostMapping("/upload-profile-image")
    public String uploadImage(@RequestParam("file") MultipartFile file){
        
        try {
            myUserService.saveProfileImage(file);
        } catch (Exception ex) {
            System.out.println(ex);

        }
        
        return "redirect:/profile-page";
    }

}
