package com.Project.PetBook.Services;

import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Models.VerificationToken;


public interface VerificationTokenInterface {

    public VerificationToken findToken(String token);

    public VerificationToken findTokenByUser(MyUser myUser);

    public void saveToken(MyUser myUser, String token);

}
