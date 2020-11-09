
package com.Project.PetBook.Services;

import com.Project.PetBook.Models.MyUser;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MyUserServiceInterface extends UserDetailsService {
    
       public void insertUser (MyUser user);
       
       public List<MyUser> getSuggestedFriends();
       
       public MyUser getUserByUsername(String userName);
}
