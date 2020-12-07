package com.Project.PetBook.Services;

import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Models.VerificationToken;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MyUserService extends UserDetailsService {

    public MyUser insertUser(MyUser user);

    public List<MyUser> getSuggestedFriends(Pageable pageable);

    public MyUser getUserByUsername(String userName);

    public MyUser getUserById(int id);

    public List<MyUser> getFriendList();

    public MyUser getUserByEmail(String email);

    public void updatePassword(String password, Integer userId);

    public MyUser register(MyUser myUser);

    public String verifyUser(VerificationToken verificationToken);

    public boolean checkIfUserNameExists(String username);

    public boolean checkIfEmailExists(String email);
}
