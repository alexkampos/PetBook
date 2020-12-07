package com.Project.PetBook.validators;

import com.Project.PetBook.Dto.RegisterDto;
import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.constraints.Credential;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;

public class CredentialValidator implements ConstraintValidator<Credential, RegisterDto> {

    private String vet;

    private String credential;

    private String message;

    @Override
    public void initialize(Credential c) {
        vet = c.vet();
        credential = c.credential();
        message = c.message();
    }

    @Override
    public boolean isValid(RegisterDto registerDto, ConstraintValidatorContext cvc) {
        boolean valid = true;
        Pattern p = Pattern.compile("^(0|[1-9][0-9]*)$");

        if (registerDto.isVet()) {
             
            String credentialNumber = registerDto.getCredentialNumber();
            Matcher m = p.matcher(credentialNumber);
            valid = m.matches();
        } 

        if (!valid) {
            cvc.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(credential)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();

        }
        return valid;
    }

}
