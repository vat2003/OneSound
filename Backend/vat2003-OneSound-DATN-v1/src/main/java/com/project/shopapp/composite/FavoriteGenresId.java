package com.project.shopapp.composite;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteGenresId implements Serializable {

	private Long accountId;
	private Long genresId;

	@Override
	public int hashCode() {
		return Objects.hash(accountId, genresId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FavoriteGenresId other = (FavoriteGenresId) obj;
		return Objects.equals(accountId, other.accountId) && Objects.equals(genresId, other.genresId);
	}

}
