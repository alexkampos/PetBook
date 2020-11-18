package com.Project.PetBook.controllers;

import com.Project.PetBook.Repos.MyUserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.ResponseBody;

@Controller

public class AjaxValidationController {

    @Autowired
    MyUserRepo myUserRepo;

    @GetMapping("/user-name-validation/{name}")
    @ResponseBody

    public boolean checkIfUserNameExists(@PathVariable String name) {

        if (myUserRepo.findByUserName(name) == null) {
            return true;

        }

        return false;
    }

    @GetMapping("/email-validation/{email}")
    @ResponseBody

    public boolean checkIfEmailExists(@PathVariable String email) {
        System.out.println("asd");
        if (myUserRepo.findByEmail(email) == null) {
            return true;
        }

        return false;
    }

}
