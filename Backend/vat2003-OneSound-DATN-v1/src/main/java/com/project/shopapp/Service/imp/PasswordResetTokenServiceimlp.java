package com.project.shopapp.Service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.PasswordResetTokenService;
import com.project.shopapp.entity.PasswordResetToken;
import com.project.shopapp.repository.GenreDAO;
import com.project.shopapp.repository.TokenRepositoryDAO;

@Service
public class PasswordResetTokenServiceimlp implements PasswordResetTokenService {
    @Autowired
    private TokenRepositoryDAO TokenRepositoryDAO;

    @Override
    public List<PasswordResetToken> getAllPasswordResetToken() {
        return TokenRepositoryDAO.findAll();
    }

    @Override
    public void DeletePasswordResetToken(int id) {
        TokenRepositoryDAO.DeletePasswordResetToken(id);
    }

}
