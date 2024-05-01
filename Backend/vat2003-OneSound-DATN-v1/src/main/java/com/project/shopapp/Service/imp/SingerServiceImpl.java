package com.project.shopapp.Service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.project.shopapp.Service.SingerService;
import com.project.shopapp.entity.Singer;
import com.project.shopapp.entity.SingerFullInfoDTO;
import com.project.shopapp.repository.SingerDAO;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerDAO singerRepository;

    @Override
    public List<Singer> getAllSingers() {
        return singerRepository.findAll();
    }

    @Override
    public Singer getSingerById(Long id) {
        return singerRepository.findById(id).orElse(null);
    }

    @Override
    public Singer updateSinger(Long id, Singer singer) {
        Singer employeeToUpdate = singerRepository.findById(id).orElse(null);
        employeeToUpdate.setFullname(singer.getFullname());
        employeeToUpdate.setDescription(singer.getDescription());
        employeeToUpdate.setImage(singer.getImage());
        employeeToUpdate.setActive(singer.isActive());
        return singerRepository.save(employeeToUpdate); // Handle not found case
    }

    @Override
    public void deleteSinger(Long id) {
        singerRepository.deleteById(id);
    }

    @Override
    public Page<Singer> getAllSingers(org.springframework.data.domain.Pageable pageable) {
        return singerRepository.findAll(pageable);

    }

    @Override
    public Singer createSinger(Singer singer) {

        return singerRepository.save(singer);
    }

    @Override
    public Singer getSingersByName(String name) {
        return singerRepository.findByFullnameIgnoreCaseContaining(name);

    }

    @Override
    public List<Singer> findAllSingerByAlbumId(Long albumId) {
        return singerRepository.findAllSingerByAlbumId(albumId);
    }

    @Override
    public SingerFullInfoDTO getSingerFullInfoById(Long id) {
        // Lấy thông tin từ singerRepository
        Singer singer = singerRepository.findById(id).orElse(null);

        // Tạo đối tượng SingerFullInfoDTO và set thông tin
        SingerFullInfoDTO singerFullInfoDTO = new SingerFullInfoDTO();
        singerFullInfoDTO.setId(singer.getId());
        singerFullInfoDTO.setFullname(singer.getFullname());
        singerFullInfoDTO.setDescription(singer.getDescription());
        singerFullInfoDTO.setImage(singer.getImage());
        singerFullInfoDTO.setSingerAlbums(singer.getSingerAlbums());
        singerFullInfoDTO.setSongSinger(singer.getSongSinger());
        singerFullInfoDTO.setFavoriteSinger(singer.getFavoriteSinger());

        return singerFullInfoDTO;
    }
    @Override
    public List<Singer> findAllSingerActive() {
        return singerRepository.findByActiveTrue();
    }
}
