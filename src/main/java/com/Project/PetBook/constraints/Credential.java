/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Project.PetBook.constraints;

import com.Project.PetBook.validators.CredentialValidator;
import com.Project.PetBook.validators.FieldsMatchValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author Alkis
 */
@Constraint(validatedBy = CredentialValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Credential {

    String vet();
    
    String credential();
    
    String message() default 
            "Invalid inputs.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
