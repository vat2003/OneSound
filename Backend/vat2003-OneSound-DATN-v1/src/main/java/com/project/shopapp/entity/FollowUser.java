package com.project.shopapp.entity;

import com.project.shopapp.composite.FollowUserId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "followUsers")
public class FollowUser {

	@EmbeddedId
	private FollowUserId id;

	@ManyToOne
	@JoinColumn(name = "accountId", insertable = false, updatable = false)
	private Account user;

	@ManyToOne
	@JoinColumn(name = "followingId", insertable = false, updatable = false)
	private Account following;

}
