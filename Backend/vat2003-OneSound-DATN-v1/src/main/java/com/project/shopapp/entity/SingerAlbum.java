package com.project.shopapp.entity;

import com.project.shopapp.composite.SingerAlbumId;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SingerAlbum")
public class SingerAlbum {

	@EmbeddedId
	private SingerAlbumId id;

	@ManyToOne
	@JoinColumn(name = "singerId", insertable = false, updatable = false)
	private Singer singer;

	@ManyToOne
	@JoinColumn(name = "albumId", insertable = false, updatable = false)
	private Album album;
}
