package com.Project.PetBook.controllers;

import com.Project.PetBook.Models.FriendRequest;
import com.Project.PetBook.Models.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.Project.PetBook.Services.MyUserService;
import com.Project.PetBook.Services.FriendRequestService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/requests")
public class FriendRequestController {

    @Autowired
    private FriendRequestService requestInterface;

    @Autowired
    private MyUserService myUserService;

    @GetMapping("/sent-friend-requests")
    public String showSentFriendRequests(ModelMap mm) {
        mm.addAttribute("sentFriendRequests", requestInterface.getTheSentFriendRequests());

        return "friends/sent-friend-requests";
    }

    @GetMapping("/received-friend-requests")
    @ResponseBody
    public List<FriendRequest> showReceivedFriendRequests(ModelMap mm) {
//        mm.addAttribute("receivedFriendRequests", requestInterface.getTheReceivedFriendRequests());
        return  requestInterface.getTheReceivedFriendRequests();
    }
    
    @GetMapping("asd")
    @ResponseBody
    public String asd(){
        return "asd";
    }
    
    @GetMapping("/suggested-friends")
    @ResponseBody
    public ResponseEntity<List<MyUser>> giveSuggestedFriends(@RequestParam int page){
        
        return new ResponseEntity<>(myUserService.getSuggestedFriends(PageRequest.of(page, 3)),HttpStatus.OK);
    }

    @GetMapping("/check-suggested-friends-results")
    @ResponseBody
    public ResponseEntity<Boolean> checkIfSuggestedFriendsLeft(@RequestParam int page){
        return new ResponseEntity<>(myUserService.getSuggestedFriends(PageRequest.of(page,3)).isEmpty(),HttpStatus.OK);
    }
    

    @GetMapping("/addFriend/{id}")
    @ResponseBody
    public ResponseEntity sendFriendRequest(@PathVariable int id) {
        
        requestInterface.sendRequest(id);
        
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("requestAccepted/{id}")
    public ResponseEntity setFriendRequestAccepted(@PathVariable int id) {

        requestInterface.acceptRequest(id);
        return new ResponseEntity(HttpStatus.OK);

    }

    @GetMapping("requestDeclined/{id}")
    public ResponseEntity setFriendRequestRejected(@PathVariable int id) {

        requestInterface.rejectRequest(id);
        return new ResponseEntity(HttpStatus.OK);

    }
}
