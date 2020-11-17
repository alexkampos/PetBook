package com.Project.PetBook.constraints;

import com.Project.PetBook.validators.FieldsMatchValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = FieldsMatchValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldsMatch {

    String firstParameter();
    
    String secondParameter();
    
    String message() default 
            "Invalid inputs.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
