
package com.Project.PetBook.Repos;

import com.Project.PetBook.Models.Friendship;
import com.Project.PetBook.Models.MyUser;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FriendshipRepo extends JpaRepository<Friendship, Integer> {

    @Query(
            value = "SELECT * FROM  friendships \n"
            + "where friend_one = ?1 \n"
            + "or friend_two= ?1",
            nativeQuery = true)
    public List<Friendship> findByFriendOneOrFriendTwo(MyUser currentUser);

}
