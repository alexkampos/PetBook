package com.Project.PetBook.controllers;

import com.Project.PetBook.Repos.MyUserRepo;
import com.Project.PetBook.Services.MyUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AjaxValidationController {

    @Autowired
    MyUserService userService;

    @ResponseBody
    @GetMapping("/user-name-validation/{name}")
    public boolean checkIfUserNameExists(@PathVariable String name) {

        return userService.checkIfUserNameNotExists(name);
    }

    @ResponseBody
    @GetMapping("/email-validation/{email}")
    public boolean checkIfEmailExists(@PathVariable String email) {

        return userService.checkIfEmailNotExists(email);
    }

}
