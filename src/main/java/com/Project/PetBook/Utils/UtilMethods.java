package com.Project.PetBook.Utils;

import com.Project.PetBook.Dto.RegisterDto;
import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Services.MyUserServiceInterface;
import com.Project.PetBook.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UtilMethods {

    @Autowired
    MyUserServiceInterface myUserServiceInterface;

    public UtilMethods() {
    }

    public MyUser getLoggedInUser() {

        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        return myUserServiceInterface.getUserByUsername(loggedInUserName);
    }

    public MyUser convertDtoUserToMyUser(RegisterDto registerDto) {
        MyUser myUser = new MyUser();

        myUser.setUserName(registerDto.getUserName());
        myUser.setEmail(registerDto.getEmail());
        myUser.setUserPassword(registerDto.getUserPassword());
        System.out.println(registerDto.getUserName());

        return myUser;
    }

}

//    private MyUserServiceInterface getMyUserService() {
//        return SpringContext.getBean(MyUserServiceInterface.class);
//    }
