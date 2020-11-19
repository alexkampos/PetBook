package com.Project.PetBook.Services;

import com.Project.PetBook.Models.Friendships;
import com.Project.PetBook.Models.Role;
import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Models.VerificationToken;
import com.Project.PetBook.Repos.FriendshipsRepo;
import com.Project.PetBook.Repos.MyUserRepo;
import com.Project.PetBook.Repos.RoleRepo;
import com.Project.PetBook.Utils.UtilMethods;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class MyUserServiceImplimentation implements MyUserServiceInterface {

    @Autowired
    private MyUserRepo myUserRepo;

    @Autowired
    private UtilMethods utilMethods;

    @Autowired
    private FriendshipsServiceInterface friendshipsServiceInterface;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private VerificationTokenInterface tokenInterface;

    @Autowired
    private EmailService emailService;

    @Autowired
    private RoleRepo roleRepo;

    
    
    
    
    // // // // Spring Security // // // //
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        MyUser myuser = myUserRepo.findByUserName(userName);
        if (myuser == null) {

            throw new UsernameNotFoundException("User name not found");
        }

        boolean enabled = !myuser.isEnabled();
        UserDetails springSecurityUser = User.withUsername(myuser.getUserName())
                .password(myuser.getUserPassword())
                .disabled(enabled)
                .authorities(mapRolesToAuthorities((List) myuser.getRoles()))
                .build();

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
    public MyUser insertUser(MyUser user) {
        return myUserRepo.save(user);
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
        
        List<Friendships> friendshipList = friendshipsServiceInterface.getFriendshipList(utilMethods.getLoggedInUser());
        List<MyUser> friendList = new ArrayList<>();
        for (Friendships fs : friendshipList) {
            if (fs.getFriendOne().getUserId() == utilMethods.getLoggedInUser().getUserId()) {
                friendList.add(fs.getFriendTwo());
            } else if (fs.getFriendTwo().getUserId() == utilMethods.getLoggedInUser().getUserId()) {
                friendList.add(fs.getFriendOne());
            }
        }
        return friendList;
    }
    
    

    @Override
    public MyUser register(MyUser myUser) {

        myUser.setUserPassword(passwordEncoder.encode(myUser.getUserPassword()));

        myUser.setEnabled(false);

        Optional<MyUser> saved = Optional.of(insertUser(myUser));

        saved.ifPresent(u -> {

            try {
                String token = UUID.randomUUID().toString();
                tokenInterface.saveToken(saved.get(), token);

                emailService.sendHtmlMail(u);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return insertUser(myUser);
    }

    
    
    @Override
    public String verifyUser(VerificationToken verificationToken) {
        MyUser myUser = verificationToken.getMyUser();
        if (!myUser.isEnabled()) {

           // // // Get the current timestamp // // //
            Timestamp currentTimeStamp = new Timestamp(System.currentTimeMillis());
            if (verificationToken.getExpiryDate().before(currentTimeStamp)) {
                return "your verification token is expired";
            } else {
                // // // Activate Acount // // //
                myUser.setEnabled(true);
                
               //  // // Set Role User // // //
                Role userRole = roleRepo.findById(1).orElse(null);
                List<Role> roles = new ArrayList();
                roles.add(userRole);
                myUser.setRoles(roles);

               // // // Update User // // //
                insertUser(myUser);

                return "Your acount is Now Activated";
                
                // To DO Remove Token 
                
                

            }
        } else {
            //the user is already activated
            return "your acount is already acivated";
        }

    }

    @Override
    public boolean checkIfUserNameNotExists(String username) {

        return myUserRepo.findByUserName(username) == null;
    }

    @Override
    public boolean checkIfEmailNotExists(String email) {

        return myUserRepo.findByEmail(email) == null;
    }

}
