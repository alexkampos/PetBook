
package com.Project.PetBook.Services;

import com.Project.PetBook.Models.MyUser;
import com.Project.PetBook.Models.PasswordResetToken;

public interface PasswordResetTokenService {
    
    public PasswordResetToken createPasswordResetToken(MyUser myUser);
    
}
