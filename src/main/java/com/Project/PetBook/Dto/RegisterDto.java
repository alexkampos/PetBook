package com.Project.PetBook.Dto;

import com.Project.PetBook.constraints.Credential;
import com.Project.PetBook.constraints.FieldsMatch;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@FieldsMatch(firstParameter = "confirmPassword", secondParameter = "userPassword",message="Password doesn't match")
@Credential(vet = "vet", credential = "credentialNumber",message="Invalid Credential Id")
public class RegisterDto {


    @Pattern(regexp = "^[a-zA-Z](_(?!(\\.|_))|\\.(?!(_|\\.))|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$", message = "Username  must be at least 3 characters  and start with a letter")
    private String userName;

    @NotBlank(message = "Email cannot be empty")
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$", message = "Password must be at least 8 characters "
              + "and have at least one upper case letter (A-Z), one lower case letter (a-z), one digit (0-9) and one special character (#?!@$%^&-)!")
    private String userPassword;

    @NotBlank(message = "Password cannot be empty")
    private String confirmPassword;

    private boolean vet;


    private String credentialNumber;

    public RegisterDto() {
    }

    public RegisterDto(String userName, String email, String userPassword, String confirmPassword) {
        this.userName = userName;
        this.email = email;
        this.userPassword = userPassword;
        this.confirmPassword = confirmPassword;
    }

    public RegisterDto(String userName, String email, String userPassword, String confirmPassword, boolean vet, String credentialNumber) {
        this.userName = userName;
        this.email = email;
        this.userPassword = userPassword;
        this.confirmPassword = confirmPassword;
        this.vet = vet;
        this.credentialNumber = credentialNumber;
    }

    public boolean isVet() {
        return vet;
    }

    public void setVet(boolean vet) {
        this.vet = vet;
    }

    public String getCredentialNumber() {
        return credentialNumber;
    }

    public void setCredentialNumber(String credentialNumber) {
        this.credentialNumber = credentialNumber;
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

}
