
package com.Project.PetBook.Dto;

import javax.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.Project.PetBook.constraints.FieldsMatch;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@FieldsMatch(firstParameter = "password", secondParameter = "confirmPassword", message = "The password fields must match!")
public class PasswordResetDto {
    
    @NotEmpty
    private String password;
    
    @NotEmpty
    private String confirmPassword;
    
    @NotEmpty
    private String token;
    
    
    
}
