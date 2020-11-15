
package com.Project.PetBook.Utils;

import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Services.MyUserServiceInterface;
//import com.Project.PetBook.SpringContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UtilMethods {
    
    @Autowired
    MyUserServiceInterface myUserServiceInterface;
    
    public UtilMethods(){}
    
//    private MyUserServiceInterface getMyUserService() {
//        return SpringContext.getBean(MyUserServiceInterface.class);
//    }
//    
    public MyUser getLoggedInUser(){
        
        String loggedInUserName = SecurityContextHolder.getContext().getAuthentication().getName();
        return myUserServiceInterface.getUserByUsername(loggedInUserName);
//        getMyUserService()
    }
    
}
