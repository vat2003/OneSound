package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.dto.EmailDTO;
import com.project.shopapp.entity.Email;
import com.project.shopapp.security.DataNotFoundException;

public interface EmailService {
    Email createUser(EmailDTO emailDTO);

    Email getUserById(long id) throws DataNotFoundException;

    List<Email> getAllUsers();

    void deleteCoupon(long id);

    Email getUserByEmail(String email);
}
