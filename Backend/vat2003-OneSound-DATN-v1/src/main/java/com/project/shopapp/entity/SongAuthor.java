package com.project.shopapp.entity;


import com.project.shopapp.composite.SongAuthorId;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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
@Table(name = "SongAuthors")
public class SongAuthor {
	@EmbeddedId
	private SongAuthorId id;

	@ManyToOne
	@JoinColumn(name = "songId", insertable = false, updatable = false)
	private Song song;
	
	@ManyToOne
	@JoinColumn(name = "authorId", insertable = false, updatable = false)
	private Author author;
}
