
package com.Project.PetBook.Repos;

import com.Project.PetBook.Models.MyUser;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepo extends JpaRepository<MyUser, Integer> {

    public MyUser findByUserName(String userName);

    public List<MyUser> findByUserIdNot(int userId);
    
    public MyUser findByEmail(String email);

    @Query(
            value = "SELECT * \n"
            + "FROM users u\n"
            + "WHERE (u.user_id NOT IN (SELECT f.friend_one FROM friendships f WHERE f.friend_two = ?1))\n"
            + "AND (u.user_id NOT IN (SELECT f.friend_two from friendships f WHERE f.friend_one = ?1))\n"
            + "AND (u.user_id NOT IN (SELECT fr.sender_id from friend_requests fr WHERE fr.receiver_id = ?1))\n"
            + "AND (u.user_id NOT IN (SELECT fr.receiver_id from friend_requests fr WHERE fr.sender_id = ?1))\n"
            + "AND u.user_id != ?1",
            nativeQuery = true
    )
    public List<MyUser> findSuggestedFriends(int userId, Pageable pageable);
    
    @Modifying
    @Query(value = "UPDATE USERS u SET u.user_password = ?1 WHERE u.user_id = ?2",
            nativeQuery = true)
    void updatePassword(String password, Integer id);
}
