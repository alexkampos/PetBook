package com.Project.PetBook.Services;

import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Models.PasswordResetToken;
import com.Project.PetBook.Pojos.Mail;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Autowired
    private HttpServletRequest request;

    public void sendEmail(Mail mail) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            Context context = new Context();
            context.setVariables(mail.getModel());
            String html = templateEngine.process("email/email-template", context);

            helper.setTo(mail.getTo());
            helper.setText(html, true);
            helper.setSubject(mail.getSubject());
            helper.setFrom(mail.getFrom());

            emailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createMailModel(Mail mail, MyUser user, PasswordResetToken token) {

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "PetBook Team");
        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        model.put("resetUrl", url + "/PetBook/reset-password?token=" + token.getToken());
        mail.setModel(model);

    }

}
