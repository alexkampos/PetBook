package com.Project.PetBook.controllers;

import com.Project.PetBook.Dto.RegisterDto;
import com.Project.PetBook.Dto.PasswordForgotDto;
import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Models.VerificationToken;
import com.Project.PetBook.Services.MyUserService;
import com.Project.PetBook.Services.VerificationTokenInterface;
import com.Project.PetBook.Utils.UtilMethods;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private MyUserService myUserServiceInterface;

    @Autowired
    VerificationTokenInterface tokenInterface;

    @Autowired
    UtilMethods myMethods;

    @GetMapping("/")
    public String login(ModelMap mm) {
        mm.addAttribute("passwordResetDto", new PasswordForgotDto());
        return "authentication/sign-in-form";
    }

//    @GetMapping("/")
//    public String welcome() {
//        return "home/welcome";
//    }
    @GetMapping("/home")
    public String showHomeFromGet() {
        return "home/home";
    }

    @PostMapping("/home")
    public String showHomeFromPost() {
        return "home/home";
    }

    @GetMapping("/registerUser")
    public String showForm(RegisterDto registerDto) {

        return "registration/register";
    }

    @PostMapping("/registerUser")
    public String submitForm(@Valid RegisterDto registerDto, BindingResult bindingResult, RedirectAttributes redirectAttributes, ModelMap mm) {

        // // // Back end Validation // // // 
        if (bindingResult.hasErrors()) {

            return "registration/register";
        }

        // // // Check if User name or Email already exists // // // 
        if (!myUserServiceInterface.checkIfUserNameExists(registerDto.getUserName()) && !myUserServiceInterface.checkIfEmailExists(registerDto.getEmail())) {

            MyUser myUser = myMethods.convertDtoUserToMyUser(registerDto);

            myUserServiceInterface.register(myUser);

            mm.addAttribute("msg", "Confirmation email Has been send to your email!!");

            return "/succes-email-sent";
        } else {
            mm.addAttribute("msg", "Something Went Wrong :(");
            return "authentication/sign-in-form";
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

        return "authentication/sign-in-form";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "authentication/sign-in-form";
    }


}
