package com.project.shopapp.Service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.FacebookService;
import com.project.shopapp.dto.FacebookDTO;
import com.project.shopapp.entity.Facebook;
import com.project.shopapp.repository.FacebookDao;
import com.project.shopapp.security.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacebookServiceimlp implements FacebookService {

    @Autowired
    private FacebookDao FacebookDao;

    @Override
    public Facebook createUser(FacebookDTO facebookDTO) {
        if (FacebookDao.findByEmail(facebookDTO.getEmail()).isEmpty()) {
            Facebook facebook = Facebook.builder()
                    .email(facebookDTO.getEmail())
                    .name(facebookDTO.getName())
                    .facebookId(facebookDTO.getFacebookId())
                    .build();
            return FacebookDao.save(facebook);
        }
        return null;
    }

    @Override
    public Facebook getUserById(long id) throws DataNotFoundException {
        return this.FacebookDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can not find email with id: " + id));
    }

    @Override
    public List<Facebook> getAllUsers() {
        return this.FacebookDao.findAll();
    }

    @Override
    public void deleteAccount(long id) {
        this.FacebookDao.deleteById(id);
    }

    @Override
    public Facebook getFacebookByEmail(String email) {
        return this.FacebookDao.findUserByEmail(email);
    }

}
