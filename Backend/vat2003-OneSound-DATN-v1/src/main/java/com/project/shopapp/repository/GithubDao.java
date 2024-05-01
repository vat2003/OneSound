package com.project.shopapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.entity.Facebook;
import com.project.shopapp.entity.Github;

public interface GithubDao extends JpaRepository<Github, Long> {
    List<Github> findByEmail(String email);

    Github findUserByEmail(String email);

    Github findByGithubId(String GithubId);
}
