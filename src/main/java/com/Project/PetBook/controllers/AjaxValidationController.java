package com.Project.PetBook.controllers;

import com.Project.PetBook.Services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AjaxValidationController {

    @Autowired
    MyUserService userService;

    @GetMapping("/user-name-validation/{name}")
    public boolean checkIfUserNameExists(@PathVariable String name) {

        return userService.checkIfUserNameExists(name);
        
    }

    @GetMapping("/email-validation/{email}")
    public boolean checkIfEmailExists(@PathVariable String email) {

        return userService.checkIfEmailExists(email);
        
    }

}
