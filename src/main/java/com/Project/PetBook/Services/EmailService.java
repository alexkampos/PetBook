
package com.Project.PetBook.Services;

import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Models.VerificationToken;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;



@Service
public class EmailService {
    
    @Autowired
    VerificationTokenServiceImplimentation verificationTokenServiceImplimentation;
    
    @Autowired
    TemplateEngine templateEngine;
    
    @Autowired
    JavaMailSender javaMailSender;
    
   
    public void sendHtmlMail(MyUser myUser) throws javax.mail.MessagingException{
    
        VerificationToken  verificationToken = verificationTokenServiceImplimentation.findTokenByUser(myUser);
        //check if the user has the token
        if(verificationToken !=null){
        String token = verificationToken.getToken();
        Context context = new Context();
        context.setVariable("title", "Verify your email address");
        context.setVariable("link", "http://localhost:8080/PetBook/activation?token="+token);
        //create an html template and pass the variables 
        
        String body = templateEngine.process("verification", context);
        //send the verification email
        
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(myUser.getEmail());
            helper.setSubject("email address verification");
            helper.setText(body,true);
            javaMailSender.send(message);
           
        }
    
    
    }
    
}
