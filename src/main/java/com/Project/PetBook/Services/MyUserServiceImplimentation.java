package com.Project.PetBook.Services;

import com.Project.PetBook.Models.Friendship;
import com.Project.PetBook.Models.Role;
import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Repos.MyUserRepo;
import com.Project.PetBook.Utils.UtilMethods;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MyUserServiceImplimentation implements MyUserService {

    @Autowired
    private MyUserRepo myUserRepo;

    @Autowired
    private UtilMethods utilMethods;

    @Autowired
    private FriendshipService friendshipsServiceInterface;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        MyUser myuser = myUserRepo.findByUserName(userName);
        if (myuser == null) {

            throw new UsernameNotFoundException("User name not found");
        }
        User springSecurityUser = new User(myuser.getUserName(), myuser.getUserPassword(), mapRolesToAuthorities((List) myuser.getRoles()));
        return springSecurityUser;
    }

    private List<? extends GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
        List<GrantedAuthority> authorities = new ArrayList();
        for (Role role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public void insertUser(MyUser user) {
        myUserRepo.save(user);
    }

    @Override
    public List<MyUser> getSuggestedFriends() {
        return myUserRepo.findSuggestedFriends(utilMethods.getLoggedInUser().getUserId());
    }

    @Override
    public MyUser getUserByUsername(String userName) {
        return myUserRepo.findByUserName(userName);
    }

    @Override
    public MyUser getUserById(int id) {
        return myUserRepo.getOne(id);
    }

    @Override
    public List<MyUser> getFriendList() {
        List<Friendship> friendshipList = friendshipsServiceInterface.getFriendshipList(utilMethods.getLoggedInUser());
        List<MyUser> friendList = new ArrayList<>();
        friendshipList.forEach((fs) -> {
            if (fs.getFriendOne().getUserId() == utilMethods.getLoggedInUser().getUserId()) {
                friendList.add(fs.getFriendTwo());
            } else if (fs.getFriendTwo().getUserId() == utilMethods.getLoggedInUser().getUserId()) {
                friendList.add(fs.getFriendOne());
            }
        });
        return friendList;
    }

    @Override
    public MyUser getUserByEmail(String email) {
        return myUserRepo.findByEmail(email);
    }

    @Override
    public void updatePassword(String password, Integer userId) {
        myUserRepo.updatePassword(password, userId);
    }
    
    

}
