package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.dto.FacebookDTO;
import com.project.shopapp.entity.Facebook;
import com.project.shopapp.security.DataNotFoundException;

public interface FacebookService {
    Facebook createUser(FacebookDTO facebookDTO);

    Facebook getUserById(long id) throws DataNotFoundException;

    List<Facebook> getAllUsers();

    void deleteAccount(long id);

    Facebook getFacebookByEmail(String email);
}
