package com.project.shopapp.composite;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistSongId implements Serializable {
	private Long playlistId;
	private Long songId;

	@Override
	public int hashCode() {
		return Objects.hash(playlistId, songId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PlaylistSongId other = (PlaylistSongId) obj;
		return Objects.equals(playlistId, other.playlistId) && Objects.equals(songId, other.songId);
	}

}
