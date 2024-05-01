package com.project.shopapp.Service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.FacebookService;
import com.project.shopapp.Service.GithubService;
import com.project.shopapp.dto.GitDTO;
import com.project.shopapp.entity.Facebook;
import com.project.shopapp.entity.Github;
import com.project.shopapp.repository.FacebookDao;
import com.project.shopapp.repository.GithubDao;
import com.project.shopapp.security.DataNotFoundException;

@Service
public class GithubServiceimlp implements GithubService {

    @Autowired
    private GithubDao GithubDao;

    @Override
    public Github createUser(GitDTO GitDTO) {
        if (GithubDao.findByEmail(GitDTO.getEmail()).isEmpty()) {
            Github facebook = Github.builder()
                    .email(GitDTO.getEmail())
                    .name(GitDTO.getName())
                    .githubId(GitDTO.getGithubId())
                    .build();
            return GithubDao.save(facebook);
        }
        return null;
    }

    @Override
    public Github getUserById(long id) throws DataNotFoundException {
        return this.GithubDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can not find email with id: " + id));
    }

    @Override
    public List<Github> getAllUsers() {
        return this.GithubDao.findAll();
    }

    @Override
    public void deleteAccount(long id) {
        this.GithubDao.deleteById(id);
    }

    @Override
    public Github getGithubByEmail(String email) {
        return this.GithubDao.findUserByEmail(email);
    }

}
