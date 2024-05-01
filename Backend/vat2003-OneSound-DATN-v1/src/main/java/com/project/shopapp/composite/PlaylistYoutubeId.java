package com.project.shopapp.composite;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaylistYoutubeId implements Serializable {
    private Long playlistId;
    private String youtubeId;

    @Override
    public int hashCode() {
        return Objects.hash(playlistId, youtubeId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PlaylistYoutubeId other = (PlaylistYoutubeId) obj;
        return Objects.equals(playlistId, other.playlistId) && Objects.equals(youtubeId, other.youtubeId);
    }
}
