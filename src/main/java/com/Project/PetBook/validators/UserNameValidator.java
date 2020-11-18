package com.Project.PetBook.validators;

import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Repos.MyUserRepo;
import com.Project.PetBook.constrains.UserNameConst;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

public class UserNameValidator implements ConstraintValidator<UserNameConst, String> {

    @Autowired
    MyUserRepo myUserRepo;

    @Override
    public void initialize(UserNameConst a) {
      
    }

       @Override
      public boolean isValid(String userName, ConstraintValidatorContext cvc) {
        MyUser myUser = myUserRepo.findByUserName(userName);
        System.out.println("asdf");

        if (userName.length() >= 3 && userName.length() <= 20 && myUser == null) {
            return true;
        }

        return false;
    }


}
