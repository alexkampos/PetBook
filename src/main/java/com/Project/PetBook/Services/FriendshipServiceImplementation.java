
package com.Project.PetBook.Services;

import com.Project.PetBook.Models.Friendship;
import com.Project.PetBook.Models.MyUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Project.PetBook.Repos.FriendshipRepo;

@Service
public class FriendshipServiceImplementation implements FriendshipService {

    @Autowired
    private FriendshipRepo friendshipRepo;

    @Override
    public void insertFrienship(Friendship friendship) {

        friendshipRepo.save(friendship);
    }

    @Override
    public List<Friendship> getFriendshipList(MyUser currentUser) {
        return friendshipRepo.findByFriendOneOrFriendTwo(currentUser);
    }

}
