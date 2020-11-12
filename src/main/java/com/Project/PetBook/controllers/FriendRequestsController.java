package com.Project.PetBook.controllers;

import com.Project.PetBook.Models.FriendRequest;
import com.Project.PetBook.Models.FriendRequestStatus;
import com.Project.PetBook.Models.Friendships;
import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Services.FriendRequestServiceInterface;
import com.Project.PetBook.Services.FriendRequestStatusServiceInterface;
import com.Project.PetBook.Services.FriendshipsServiceInterface;
import com.Project.PetBook.Services.MyUserServiceInterface;
import com.Project.PetBook.Utils.UtilMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    @GetMapping("/addFriend/{id}")
    @ResponseBody
    public String friendAddition(@PathVariable int id) {     
        
        requestInterface.requestSend(id);
        
        return "";
    }
    
    @GetMapping("requestAccepted/{id}")
    @ResponseBody
    public String acceptedRequest(@PathVariable int id) {
        
        requestInterface.requestAccepted(id);
        
        return "";
    }
    
    @GetMapping("requestDeclined/{id}")
    @ResponseBody
    public String requestDeclined(@PathVariable int id) {

        requestInterface.requestRejected(id);
        
        return "";
    }
}
