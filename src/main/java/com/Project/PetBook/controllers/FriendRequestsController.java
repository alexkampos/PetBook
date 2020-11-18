package com.Project.PetBook.controllers;

import com.Project.PetBook.Services.FriendRequestServiceInterface;
import com.Project.PetBook.Services.MyUserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
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

    @ResponseBody
    @GetMapping("/addFriend/{id}")
    public String friendAddition(@PathVariable int id) {

        requestInterface.requestSend(id);

        return "";
    }

    @ResponseBody
    @GetMapping("requestAccepted/{id}")
    public String acceptedRequest(@PathVariable int id) {

        requestInterface.requestAccepted(id);

        return "";
    }

    @ResponseBody
    @GetMapping("requestDeclined/{id}")
    public String requestDeclined(@PathVariable int id) {

        requestInterface.requestRejected(id);

        return "";
    }

    @GetMapping("friend-list")
    public String friendlist(ModelMap mm) {

        mm.addAttribute("friendList", myUserServiceInterface.getFriendList());
        return "friend-list.html";
    }
}
