package com.project.shopapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.shopapp.entity.Author;

import java.util.List;


public interface AuthorDAO extends JpaRepository<Author, Long> {
	List<Author> findByFullnameIgnoreCase(String fullname);
	List<Author> findByActive(boolean active);
	@Query("SELECT a FROM Author a WHERE LOWER(a.fullname) LIKE LOWER(CONCAT('%', :fullname, '%'))")
    Page<Author> findByFullnamePage(String fullname,Pageable pageable);
}
