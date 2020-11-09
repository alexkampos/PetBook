
package com.Project.PetBook.controllers;

import com.Project.PetBook.Services.FriendRequestServiceInterface;
import com.Project.PetBook.Services.MyUserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/requests")
public class FriendRequestsController {

    @Autowired
    FriendRequestServiceInterface requestInterface;

    @Autowired
    MyUserServiceInterface myUserServiceInterface;
    
    @GetMapping("/sent-friend-requests")
    public String sentFriendRequests(ModelMap mm) {
        mm.addAttribute("sentFriendRequests", requestInterface.getSentFriendRequests());
        return "sent-friend-requests.html";
    }
    
    @GetMapping("/received-friend-requests")
    public String receivedFriendRequests(ModelMap mm) {
        mm.addAttribute("receivedFriendRequests", requestInterface.getReceivedFriendRequests());
        return "received-friend-requests.html";
    }

    
    @GetMapping("/suggested-friends")
    public String userprofile(ModelMap mm) {
        mm.addAttribute("suggestedFriends", myUserServiceInterface.getSuggestedFriends());
        return "suggested-friends.html";
    }
    
}
