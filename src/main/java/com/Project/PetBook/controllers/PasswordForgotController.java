
package com.Project.PetBook.controllers;

import com.Project.PetBook.Dto.PasswordForgotDto;
import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Models.PasswordResetToken;
import com.Project.PetBook.Pojos.Mail;
import com.Project.PetBook.Services.EmailService;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.Project.PetBook.Services.MyUserService;
import com.Project.PetBook.Services.PasswordResetTokenService;

@Controller
@RequestMapping("forgot-password")
public class PasswordForgotController {
    
    @Autowired
    private MyUserService myUserService;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private PasswordResetTokenService passwordResetTokenService;
    
    @ModelAttribute("passwordForgotDto")
    public PasswordForgotDto forgotPasswordDto() {
        return new PasswordForgotDto();
    }
    
    @GetMapping
    public String displayForgotPasswordPage() {
        return "authentication/forgot-password";
    }
    
    @PostMapping
    public String processForgotPasswordForm(@ModelAttribute("passwordForgotDto") @Valid PasswordForgotDto form,
            BindingResult result,
            HttpServletRequest request) {
        
        if (result.hasErrors()){
            return "authentication/forgot-password";
        }
        
        MyUser myUser = myUserService.getUserByEmail(form.getEmail());
        if (myUser == null){
            result.rejectValue("email", null, "We could not find an account associated with that e-mail address.");
            return "authentication/forgot-password";
        }
        
        PasswordResetToken usersPasswordResetToken = passwordResetTokenService.createPasswordResetToken(myUser);

        Mail mail = new Mail();
        mail.setFrom("PetBook");
        mail.setTo(myUser.getEmail());
        mail.setSubject("Password reset request");

        emailService.createMailModel(mail, myUser, usersPasswordResetToken);
        
        emailService.sendEmail(mail);

        return "redirect:/forgot-password?success";
        
    }
    
}
