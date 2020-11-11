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

    @Autowired
    FriendRequestStatusServiceInterface friendRequestStatusServiceInterface;

    @Autowired
    private UtilMethods utilMethods;

    @Autowired
    FriendshipsServiceInterface friendshipsServiceInterface;

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

        // Getting Curent Time 
        java.util.Date dt = new java.util.Date();

        // Getting Friend Request Status "PENDING" 
        FriendRequestStatus statusId = friendRequestStatusServiceInterface.getFriendRequestStatusByName("PENDING");

        //Getting Sender User
        MyUser senderId = utilMethods.getLoggedInUser();

        //Getting Receiver User
        MyUser receiverId = myUserServiceInterface.getUserById(id);

        FriendRequest friendRequest = new FriendRequest(dt, statusId, senderId, receiverId);

        requestInterface.insertFriendRequest(friendRequest);

        return "ola popa";
    }

    @GetMapping("requestAccepted/{id}")
    @ResponseBody
    public String acceptedRequest(@PathVariable int id) {

        // Getting Curent Time 
        java.util.Date dt = new java.util.Date();


        //Getting receiver
        MyUser friendOne = utilMethods.getLoggedInUser();
        
        //Getting sender User
        MyUser friendTwo = myUserServiceInterface.getUserById(id);
      
        requestInterface.removeFriendRequest(friendTwo, friendOne);
       
        Friendships friendships = new Friendships(dt, friendOne, friendTwo);
       
        friendshipsServiceInterface.insertFrienship(friendships);

        return "";
    }
}
