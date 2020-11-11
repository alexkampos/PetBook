package com.Project.PetBook.controllers;

import com.Project.PetBook.Models.FriendRequest;
import com.Project.PetBook.Models.FriendRequestStatus;
import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Services.FriendRequestServiceInterface;
import com.Project.PetBook.Services.FriendRequestStatusServiceInterface;
import com.Project.PetBook.Services.MyUserServiceInterface;
import com.Project.PetBook.Utils.UtilMethods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
    public String friendAddition(@PathVariable int id, ModelMap mm) {

        // Getting Curent Time 
        java.util.Date dt = new java.util.Date();
//        java.text.SimpleDateFormat sdf
//                = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String currentTime = sdf.format(dt);

        // Getting Friend Request Status "PENDING" 
        FriendRequestStatus statusId = friendRequestStatusServiceInterface.getFriendRequestStatusByName("PENDING");

        //Getting Sender User
        MyUser senderId = utilMethods.getLoggedInUser();
        
        
        //Getting Receiver User
        MyUser receiverId = myUserServiceInterface.getUserById(id);
        
        
        FriendRequest friendRequest = new FriendRequest(dt, statusId, senderId, receiverId);

        requestInterface.insertFriendRequest(friendRequest);

        mm.addAttribute("message", "Request Sent");

        return "home";
    }
}
