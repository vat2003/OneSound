package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.entity.Genre;
import com.project.shopapp.entity.PasswordResetToken;

public interface PasswordResetTokenService {

    List<PasswordResetToken> getAllPasswordResetToken();

    void DeletePasswordResetToken(int id);

}
