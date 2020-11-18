package com.Project.PetBook.controllers;

import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Models.VerificationToken;
import com.Project.PetBook.Services.MyUserServiceInterface;
import com.Project.PetBook.Services.VerificationTokenServiceImplimentation;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class Welcome {

    @Autowired
    MyUserServiceInterface myUserServiceInterface;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    VerificationTokenServiceImplimentation verificationTokenServiceImplimentation;

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
    public String submitForm(@ModelAttribute MyUser myUser ,ModelMap mm) {

        myUserServiceInterface.register(myUser);
        mm.addAttribute("msg","Confirmation email Has been send to your email!!");
        return "sign-in-form.html";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "sign-in-form.html";
    }

    @GetMapping("/activation")
    public String activation(@RequestParam("token") String token, Model model) {
        // create html page activation
        VerificationToken verificationToken = verificationTokenServiceImplimentation.findByToken(token);
        if (verificationToken == null) {
            model.addAttribute("message", "your verification token is invalid");
        } else {
            MyUser myUser = verificationToken.getMyUser();
            if (!myUser.isEnabled()) {
                // get the current timestamp

                Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
                if (verificationToken.getExpiryDate().before(currentTimeStamp)) {
                    model.addAttribute("message", "your verification token is expired");
                } else {
                    //token is valid
                    //activate use acount
                    myUser.setEnabled(true);
                    //update user
                    myUserServiceInterface.insertUser(myUser);
                    model.addAttribute("message", "Your acount is Now Activated");
                   
                }
            }else{
            //the user is already activated
            model.addAttribute("message", "your acount is already acivated");
            }
        }

        return "sign-in-form.html";

    }
}
