package com.project.shopapp.Service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.project.shopapp.entity.Singer;
import com.project.shopapp.entity.SingerFullInfoDTO;

public interface SingerService {
    List<Singer> getAllSingers();

    Singer getSingerById(Long id);

    Singer getSingersByName(String name);

    Singer createSinger(Singer singer);

    Singer updateSinger(Long id, Singer singer);

    void deleteSinger(Long id);

    Page<Singer> getAllSingers(Pageable pageable);

    List<Singer> findAllSingerByAlbumId(Long albumId);

    SingerFullInfoDTO getSingerFullInfoById(Long id);
    List<Singer> findAllSingerActive();
}
