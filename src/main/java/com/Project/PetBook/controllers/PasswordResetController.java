
package com.Project.PetBook.controllers;

import com.Project.PetBook.Dto.PasswordResetDto;
import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Models.PasswordResetToken;
import com.Project.PetBook.Repos.PasswordResetTokenRepo;
import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.Project.PetBook.Services.MyUserService;

@Controller
@RequestMapping("reset-password")
public class PasswordResetController {
    
    @Autowired
    private MyUserService myUserServiceInterface;
    
    @Autowired
    private PasswordResetTokenRepo passwordResetTokenRepo;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @ModelAttribute("passwordResetDto")
    public PasswordResetDto passwordReset(){
        return new PasswordResetDto();
    }
    
    @GetMapping
    public String displayResetPasswordPage(@RequestParam(required = false) String token,
            Model model) {
        
        PasswordResetToken resetToken = passwordResetTokenRepo.findByToken(token);
        if (resetToken == null){
            model.addAttribute("error", "Could not find password reset token.");
        } else if (resetToken.isExpired()){
            model.addAttribute("error", "Token has expired, please request a new password reset.");
        } else {
            model.addAttribute("token", resetToken.getToken());
        }

        return "authentication/reset-password";
        
    }
           
    @PostMapping
    @Transactional
    public String handlePasswordReset(@ModelAttribute("passwordResetDto") @Valid PasswordResetDto passwordResetDto,
                                      BindingResult result,
                                      RedirectAttributes redirectAttributes) {

        if (result.hasErrors()){
            redirectAttributes.addFlashAttribute(BindingResult.class.getName() + ".passwordResetDto", result);
            redirectAttributes.addFlashAttribute("passwordResetDto", passwordResetDto);
            return "redirect:/reset-password?token=" + passwordResetDto.getToken();
        }

        PasswordResetToken token = passwordResetTokenRepo.findByToken(passwordResetDto.getToken());
        MyUser user = token.getMyUser();
        String updatedPassword = passwordEncoder.encode(passwordResetDto.getPassword());
        myUserServiceInterface.updatePassword(updatedPassword, user.getUserId());
        passwordResetTokenRepo.delete(token);

        return "redirect:/login?resetSuccess";
    }
    
    
}
