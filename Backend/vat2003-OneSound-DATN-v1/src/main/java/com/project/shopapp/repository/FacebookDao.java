package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.entity.Facebook;

import java.util.List;

public interface FacebookDao extends JpaRepository<Facebook, Long> {
    List<Facebook> findByEmail(String email);

    Facebook findUserByEmail(String email);

    Facebook findByFacebookId(String facebookId);
}
