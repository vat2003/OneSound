package com.project.shopapp.entity;

import java.util.Date;

import com.project.shopapp.composite.FavoriteSongId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FavoriteSongs")
public class FavoriteSong {

	@EmbeddedId
	private FavoriteSongId id;

	@ManyToOne
	@JoinColumn(name = "accountId", insertable = false, updatable = false)
	private Account user;

	@ManyToOne
	@JoinColumn(name = "songId", nullable = false, insertable = false, updatable = false)
	private Song song;

	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	private Date likeDate = new Date();
}
