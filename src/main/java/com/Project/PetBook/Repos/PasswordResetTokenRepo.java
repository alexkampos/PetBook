
package com.Project.PetBook.Repos;

import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Models.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasswordResetTokenRepo extends JpaRepository<PasswordResetToken, Long> {
    
    public PasswordResetToken findByToken(String token);
    
    public PasswordResetToken findByMyUser(MyUser myUser);
    
}
