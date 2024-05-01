package com.project.shopapp.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.Genre;

public interface GenreService {
    List<Genre> getAllGenre();
    List<Genre> findByTitleContainingIgnoreCase(String title);

    Genre getGenreById(Long id);

    Genre createGenre(Genre Genre);

    Genre updateGenre(Long id, Genre Genre);

    Genre updateGenreActive(Long id, Genre Genre);

    void deleteGenre(Long id);

    Page<Genre> getAllGenre(Pageable pageable);

    List<Genre> findAllGenreActive();
}
