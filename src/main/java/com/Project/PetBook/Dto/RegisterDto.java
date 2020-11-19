
package com.Project.PetBook.Dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RegisterDto {
    
    private String userName;
    private String email;
    private String password;
    private String confirmPassword;

}
