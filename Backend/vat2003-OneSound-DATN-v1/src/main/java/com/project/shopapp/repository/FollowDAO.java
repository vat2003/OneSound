package com.project.shopapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.shopapp.composite.FollowUserId;
import com.project.shopapp.entity.FollowUser;

public interface FollowDAO extends JpaRepository<FollowUser, FollowUserId> {

}
