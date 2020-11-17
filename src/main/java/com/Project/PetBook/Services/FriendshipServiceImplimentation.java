
package com.Project.PetBook.Services;

import com.Project.PetBook.Models.Friendship;
import com.Project.PetBook.Models.MyUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Project.PetBook.Repos.FriendshipRepo;

@Service
public class FriendshipServiceImplimentation implements FriendshipService {

    @Autowired
    private FriendshipRepo friendshipsRepo;

    @Override
    public void insertFrienship(Friendship friendships) {

        friendshipsRepo.save(friendships);
    }

    @Override
    public List<Friendship> getFriendshipList(MyUser currentUser) {
        return friendshipsRepo.findByFriendOneOrFriendTwo(currentUser);
    }

}
