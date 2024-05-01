package com.project.shopapp.Service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.GenreService;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.Album;
import com.project.shopapp.entity.Genre;
import com.project.shopapp.repository.AccountDAO;
import com.project.shopapp.repository.GenreDAO;

@Service
public class GenreServiceimpl implements GenreService {

    @Autowired
    private GenreDAO dao;

    @Override
    public List<Genre> getAllGenre() {
        return dao.findAll();
    }

    @Override
    public List<Genre> findByTitleContainingIgnoreCase(String title) {
        return dao.findByNameContainingIgnoreCase(title);
    }

    @Override
    public Genre getGenreById(Long id) {
        // TODO Auto-generated method stub
        return dao.findById(id).orElse(null);
    }

    @Override
    public Genre createGenre(Genre Genre) {
        // TODO Auto-generated method stub
        return dao.save(Genre);
    }
    @Override
    public Genre updateGenreActive(Long id, Genre Genre) {
        Genre existingAccount = dao.findById(id).orElse(null);

        if (existingAccount == null) {
            throw new IllegalArgumentException("Account not found with id: " + id);
        }
        existingAccount.setActive(true);
        Genre updatedAccountEntity = dao.save(existingAccount);
        return updatedAccountEntity;
    }
    @Override
    public Genre updateGenre(Long id, Genre Genre) {
        Genre employeeToUpdate = dao.findById(id).orElse(null);
        employeeToUpdate.setName(Genre.getName());
        employeeToUpdate.setDescription(Genre.getDescription());
        employeeToUpdate.setImage(Genre.getImage());
        employeeToUpdate.setActive(Genre.isActive());
        return dao.save(employeeToUpdate); // Handle not found case
    }

    @Override
    public void deleteGenre(Long id) {
        // TODO Auto-generated method stub
        dao.deleteById(id);
    }

    @Override
    public Page<Genre> getAllGenre(Pageable pageable) {
        // TODO Auto-generated method stub
        return dao.findAll(pageable);
    }

    @Override
    public List<Genre> findAllGenreActive() {
        return dao.findByActiveTrue();
    }

}
