package com.project.shopapp.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Albums")
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String image;
	private Integer releaseYear;
	@Temporal(TemporalType.DATE)
	private Date albumCreateDate = new Date();
	private String description;
	private Boolean active;

	@JsonIgnore
	@OneToMany(mappedBy = "album")
	private List<SingerAlbum> singerAlbums;

	@JsonIgnore
	@OneToMany(mappedBy = "album")
	private List<Song> songs;
}
