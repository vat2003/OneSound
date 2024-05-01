package com.project.shopapp.composite;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingerAlbumId implements Serializable {

	private Long singerId;
	private Long albumId;

	@Override
	public int hashCode() {
		return Objects.hash(albumId, singerId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SingerAlbumId other = (SingerAlbumId) obj;
		return Objects.equals(albumId, other.albumId) && Objects.equals(singerId, other.singerId);
	}
}
