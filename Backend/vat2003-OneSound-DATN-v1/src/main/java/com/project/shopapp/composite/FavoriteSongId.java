package com.project.shopapp.composite;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteSongId implements Serializable {
	private Long accountId;
	private Long songId;

	@Override
	public int hashCode() {
		return Objects.hash(accountId, songId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FavoriteSongId other = (FavoriteSongId) obj;
		return Objects.equals(accountId, other.accountId) && Objects.equals(songId, other.songId);
	}

}
