package com.project.shopapp.composite;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteAlbumId implements Serializable {
	private Long accountId;
	private Long albumId;

	@Override
	public int hashCode() {
		return Objects.hash(accountId, albumId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FavoriteAlbumId other = (FavoriteAlbumId) obj;
		return Objects.equals(accountId, other.accountId) && Objects.equals(albumId, other.albumId);
	}

}
