package com.project.shopapp.entity;

import com.project.shopapp.composite.SongGenreId;

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
@Table(name = "SongGenres")
public class SongGenre {

	@EmbeddedId
	private SongGenreId id;

	@ManyToOne
	@JoinColumn(name = "songId", insertable = false, updatable = false)
	private Song song;

	@ManyToOne
	@JoinColumn(name = "genreId", insertable = false, updatable = false)
	private Genre genre;
}
