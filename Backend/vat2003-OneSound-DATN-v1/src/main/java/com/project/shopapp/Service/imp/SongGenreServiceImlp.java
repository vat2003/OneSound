package com.project.shopapp.Service.imp;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.SongGenreService;
import com.project.shopapp.Service.SongGenreService;
import com.project.shopapp.composite.SongGenreId;
import com.project.shopapp.composite.SongGenreId;
import com.project.shopapp.entity.Song;
import com.project.shopapp.entity.Genre;
import com.project.shopapp.entity.SongGenre;
import com.project.shopapp.entity.Song;
import com.project.shopapp.repository.SongDAO;
import com.project.shopapp.repository.SongGenreDAO;
import com.project.shopapp.repository.GenreDAO;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SongGenreServiceImlp implements SongGenreService {

    private final SongGenreDAO SongGenreDao;

    @Autowired
    GenreDAO Genredao;

    @Autowired
    SongDAO songdao;

    @Autowired
    public SongGenreServiceImlp(SongGenreDAO SongGenreDao) {
        this.SongGenreDao = SongGenreDao;
    }

    @Override
    public List<SongGenre> getAllSongGenres() {
        // TODO Auto-generated method stub
        return SongGenreDao.findAll();
    }

   

    @Override
    public void removeSongGenre(Long SongId) {

        // // Tìm kiếm Song
        // Song a = adao.findById(SongGenreId)
        // .orElseThrow(() -> new EntityNotFoundException("Song not found"));
        List<SongGenre> SongGenre = SongGenreDao.findBySongId(SongId);
        SongGenreDao.deleteAll(SongGenre);

    }

    @Override
    @Transactional
    public void deleteBySongId(Long songId) {
        SongGenreDao.deleteBySongId(songId);
    }

    @Override
    public SongGenre createSongGenre(SongGenreId songGenreId) {
        SongGenre ss = new SongGenre();
        
        Optional<Song> optionalSong = songdao.findById(songGenreId.getSongId());
        Optional<Genre> optionalGenre = Genredao.findById(songGenreId.getGenreId());
        
        if (optionalSong.isPresent() && optionalGenre.isPresent()) {
            Song s = optionalSong.get();
            Genre genre = optionalGenre.get();
            
            ss.setId(songGenreId);
            ss.setGenre(genre);
            ss.setSong(s);
            
            return SongGenreDao.save(ss);
        } else {
            // Xử lý khi không tìm thấy bản ghi tương ứng
            // Ví dụ: ném ra một ngoại lệ, trả về null hoặc xử lý theo ý định của bạn
            throw new NoSuchElementException("Không tìm thấy bản ghi tương ứng");
        }
    }


}
