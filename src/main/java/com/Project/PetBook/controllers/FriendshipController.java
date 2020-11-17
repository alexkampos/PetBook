
package com.Project.PetBook.controllers;

import com.Project.PetBook.Services.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("friendlist")
public class FriendshipController {
    
    @Autowired
    private MyUserService myUserServiceInterface;
    
    @GetMapping
    public String showFriendlist(ModelMap mm) {

        mm.addAttribute("friendList", myUserServiceInterface.getFriendList());
        return "friends/friend-list";

    }
    
}