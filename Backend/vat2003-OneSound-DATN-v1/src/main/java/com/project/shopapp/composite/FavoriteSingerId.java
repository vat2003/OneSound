package com.project.shopapp.composite;

import java.io.Serializable;
import java.util.Objects;

public class FavoriteSingerId implements Serializable {

	private Long accountId;
	private Long singerId;

	@Override
	public int hashCode() {
		return Objects.hash(singerId, accountId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FavoriteSingerId other = (FavoriteSingerId) obj;
		return Objects.equals(singerId, other.singerId) && Objects.equals(accountId, other.accountId);
	}

}
