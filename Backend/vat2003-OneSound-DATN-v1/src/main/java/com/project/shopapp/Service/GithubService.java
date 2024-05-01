package com.project.shopapp.Service;

import java.util.List;

import com.project.shopapp.dto.FacebookDTO;
import com.project.shopapp.dto.GitDTO;
import com.project.shopapp.entity.Facebook;
import com.project.shopapp.entity.Github;
import com.project.shopapp.security.DataNotFoundException;

public interface GithubService {
    Github createUser(GitDTO GitDTO);

    Github getUserById(long id) throws DataNotFoundException;

    List<Github> getAllUsers();

    void deleteAccount(long id);

    Github getGithubByEmail(String email);
}
