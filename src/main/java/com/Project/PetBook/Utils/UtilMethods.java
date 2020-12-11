package com.Project.PetBook.Utils;

import com.Project.PetBook.Dto.RegisterDto;
import com.Project.PetBook.Models.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.Project.PetBook.Services.MyUserService;

@Component
public class UtilMethods {

    @Autowired
    MyUserService myUserService;

    public UtilMethods() {
    }

    public MyUser getLoggedInUser() {

        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        return myUserService.getUserByUsername(loggedInUserName);
    }

    public MyUser convertDtoUserToMyUser(RegisterDto registerDto) {
        MyUser myUser = new MyUser();

        myUser.setUserName(registerDto.getUserName());
        myUser.setEmail(registerDto.getEmail());
        myUser.setUserPassword(registerDto.getUserPassword());

        return myUser;
    }

}

//    private MyUserServiceInterface getMyUserService() {
//        return SpringContext.getBean(MyUserServiceInterface.class);
//    }

