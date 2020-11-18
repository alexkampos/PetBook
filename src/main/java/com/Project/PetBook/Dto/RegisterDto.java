package com.Project.PetBook.Dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


public class RegisterDto {

    @NotBlank(message = "User Name cannot be empty")
    @Size(min = 3, max = 20, message = "User Name should be between 3-20 letters")
    @Pattern(regexp = "^([ A-Za-z]+\\s)*[ A-Za-z]+$", message = "Username should contain only letters")
    private String userName;

    @NotBlank(message = "Email cannot be empty")
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 3, max = 20, message = "Password should be between 3-20 letters")
    private String userPassword;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 3, max = 20, message = "Password should be between 3-20 letters")
    private String userPassword1;

    public RegisterDto() {
    }

    public RegisterDto(String userName, String email, String userPassword, String userPassword1) {
        this.userName = userName;
        this.email = email;
        this.userPassword = userPassword;
        this.userPassword1 = userPassword1;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserPassword1() {
        return userPassword1;
    }

    public void setUserPassword1(String userPassword1) {
        this.userPassword1 = userPassword1;
    }

}
