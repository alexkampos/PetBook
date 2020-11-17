
package com.Project.PetBook.Utils;

import com.Project.PetBook.Models.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.Project.PetBook.Services.MyUserService;

@Component
public class UtilMethods {
    
    @Autowired
    MyUserService myUserServiceInterface;
    
    public UtilMethods(){}
    
    public MyUser getLoggedInUser(){
        
        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        return myUserServiceInterface.getUserByUsername(loggedInUserName);
    }
    
}
