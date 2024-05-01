package com.project.shopapp.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.shopapp.composite.FavoriteAlbumId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@Table(name = "FavoriteAlbums")
public class FavoriteAlbum {

	@EmbeddedId
	private FavoriteAlbumId id;

	@ManyToOne
	@JoinColumn(name = "accountId", insertable = false, updatable = false)
	@JsonBackReference
	private Account user;

	@ManyToOne
	@JoinColumn(name = "albumId", nullable = false, insertable = false, updatable = false)
	private Album album;

	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	private Date likeDate = new Date();

}
