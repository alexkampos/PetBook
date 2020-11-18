
package com.Project.PetBook.constrains;

import com.Project.PetBook.validators.UserNameValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;


@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserNameValidator.class)
public @interface UserNameConst {

     String message() default 
            "Invalid inputs.";
     
     public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}
