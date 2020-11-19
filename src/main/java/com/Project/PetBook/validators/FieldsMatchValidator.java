
package com.Project.PetBook.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;
import com.Project.PetBook.constraints.FieldsMatch;

public class FieldsMatchValidator implements ConstraintValidator<FieldsMatch, Object> {

    private String firstFieldName;
    
    private String secondFieldName;
    
    private String message;
    
    @Override
    public void initialize(FieldsMatch constraintAnnotation) {
        firstFieldName = constraintAnnotation.firstParameter();
        secondFieldName = constraintAnnotation.secondParameter();
        message = constraintAnnotation.message();
    }
    
    @Override
    public boolean isValid(Object instanceWithPasswords, ConstraintValidatorContext context) {
        boolean valid = true;
        try {
            final Object firstObj = BeanUtils.getProperty( instanceWithPasswords, firstFieldName);
            final Object secondobj = BeanUtils.getProperty( instanceWithPasswords, secondFieldName);
            
            valid = firstObj == null && secondobj == null || firstObj != null && firstObj.equals(secondobj);
            
        } catch (Exception ignore) {
            // ignore
        }
        
        if(!valid) {
            
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(firstFieldName)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            
        }
        
        return valid;
        
    }
    
    
    
}
