package com.project.shopapp.entity;

import java.util.Date;

import com.project.shopapp.composite.FavoriteSingerId;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
@Table(name = "FavoriteSingers")
public class FavoriteSinger {

	@EmbeddedId
	private FavoriteSingerId id;

	@ManyToOne
	@JoinColumn(name = "accountId", insertable = false, updatable = false)
	private Account user;

	@ManyToOne
	@JoinColumn(name = "singerId", nullable = false, insertable = false, updatable = false)
	private Singer singer;

	@Temporal(TemporalType.DATE)
	@Column(name = "Createdate")
	private Date likeDate = new Date();

}
