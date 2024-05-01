package com.project.shopapp.entity;

import com.project.shopapp.composite.SongSingerId;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SongSinger")
public class SongSinger {

	@EmbeddedId
	private SongSingerId id;

	@ManyToOne
	@JoinColumn(name = "songId", insertable = false, updatable = false)
	private Song song;

	@ManyToOne
	@JoinColumn(name = "singerId", insertable = false, updatable = false)
	private Singer singer;
}
