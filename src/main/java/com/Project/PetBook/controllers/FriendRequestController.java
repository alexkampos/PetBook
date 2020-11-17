package com.Project.PetBook.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.Project.PetBook.Services.MyUserService;
import com.Project.PetBook.Services.FriendRequestService;

@Controller
@RequestMapping("/requests")
public class FriendRequestController {

    @Autowired
    private FriendRequestService requestInterface;

    @Autowired
    private MyUserService myUserServiceInterface;

    @GetMapping("/sent-friend-requests")
    public String showSentFriendRequests(ModelMap mm) {
        mm.addAttribute("sentFriendRequests", requestInterface.getSentFriendRequests());

        return "friends/sent-friend-requests";
    }

    @GetMapping("/received-friend-requests")
    public String showReceivedFriendRequests(ModelMap mm) {
        mm.addAttribute("receivedFriendRequests", requestInterface.getReceivedFriendRequests());
        return "friends/received-friend-requests";
    }

    @GetMapping("/suggested-friends")
    public String showSuggestedFriends(ModelMap mm) {
        mm.addAttribute("suggestedFriends", myUserServiceInterface.getSuggestedFriends());
        return "friends/suggested-friends";
    }

    @GetMapping("/addFriend/{id}")
    @ResponseBody
    public String sendFriendRequest(@PathVariable int id) {

        requestInterface.sendRequest(id);

        return "";
    }

    @GetMapping("requestAccepted/{id}")
    @ResponseBody
    public String setFriendRequestAccepted(@PathVariable int id) {

        requestInterface.acceptRequest(id);

        return "";
    }

    @GetMapping("requestDeclined/{id}")
    @ResponseBody
    public String setFriendRequestRejected(@PathVariable int id) {

        requestInterface.rejectRequest(id);

        return "";
    }

}
