package com.project.shopapp.composite;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FavoriteYoutubeId implements Serializable {
    private Long accountId;
    private String youtubeId;

    @Override
    public int hashCode() {
        return Objects.hash(accountId, youtubeId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        FavoriteYoutubeId other = (FavoriteYoutubeId) obj;
        return Objects.equals(accountId, other.accountId) && Objects.equals(youtubeId, other.youtubeId);
    }

}
