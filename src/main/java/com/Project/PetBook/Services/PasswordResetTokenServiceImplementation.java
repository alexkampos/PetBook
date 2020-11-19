
package com.Project.PetBook.Services;

import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Models.PasswordResetToken;
import com.Project.PetBook.Repos.PasswordResetTokenRepo;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordResetTokenServiceImplementation implements PasswordResetTokenService {
    
    @Autowired
    private PasswordResetTokenRepo passwordResetTokenRepository;
    
    @Override
    public PasswordResetToken createPasswordResetToken(MyUser myUser) {
        
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setMyUser(myUser);
        token.setExpiryDate(30);
        passwordResetTokenRepository.save(token);
        return passwordResetTokenRepository.findByMyUser(myUser);
        
    }
    
}
