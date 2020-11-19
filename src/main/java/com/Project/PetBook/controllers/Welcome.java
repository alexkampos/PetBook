package com.Project.PetBook.controllers;

import com.Project.PetBook.Dto.RegisterDto;
import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Models.VerificationToken;
import com.Project.PetBook.Services.MyUserServiceInterface;
import com.Project.PetBook.Services.VerificationTokenInterface;
import com.Project.PetBook.Services.VerificationTokenServiceImplimentation;
import com.Project.PetBook.Utils.UtilMethods;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class Welcome {

    @Autowired
    MyUserServiceInterface myUserServiceInterface;

    @Autowired
    VerificationTokenInterface tokenInterface;

    @Autowired
    UtilMethods myMethods;

    @GetMapping("/")
    public String welcome() {
        return "welcome.html";
    }

    @GetMapping("/login")
    public String login() {

        return "sign-in-form.html";
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
    public String showForm(RegisterDto registerDto) {

        return "register.html";
    }

    @PostMapping("/registerUser")
    public String submitForm(@Valid RegisterDto registerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelMap mm) {

       // // // Back end Validation // // // 
        if (bindingResult.hasErrors()) {
            return "register.html";
        }

       // // // Check if User name or Email already exists // // // 
        if (myUserServiceInterface.checkIfUserNameNotExists(registerDto.getUserName()) && myUserServiceInterface.checkIfEmailNotExists(registerDto.getEmail())) {

            
            MyUser myUser = myMethods.convertDtoUserToMyUser(registerDto);

            myUserServiceInterface.register(myUser);

            mm.addAttribute("msg", "Confirmation email Has been send to your email!!");

            
            return "sign-in-form.html";
        } else {
            mm.addAttribute("msg", "Something Went Wrong :(");
            return "sign-in-form.html";
        }
    }
    

    @GetMapping("/activation")
    public String activation(@RequestParam("token") String token, Model model) {

        VerificationToken verificationToken = tokenInterface.findToken(token);

        if (verificationToken == null) {
            
            model.addAttribute("message", "your verification token is invalid");
            
        } else {

            String message = myUserServiceInterface.verifyUser(verificationToken);

            model.addAttribute("message", message);
        }

        return "sign-in-form.html";

    }

    
    
    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "sign-in-form.html";
    }
}
