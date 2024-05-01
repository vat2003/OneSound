package com.project.shopapp.Service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.shopapp.Service.EmailService;
import com.project.shopapp.dto.EmailDTO;
import com.project.shopapp.entity.Email;
import com.project.shopapp.repository.EmailDao;
import com.project.shopapp.security.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImlp implements EmailService {

    @Autowired
    EmailDao EmailDao;

    @Override
    public Email createUser(EmailDTO emailDTO) {
        if (EmailDao.findByEmail(emailDTO.getEmail()).isEmpty()) {
            Email email = Email.builder()
                    .email(emailDTO.getEmail())
                    .name(emailDTO.getName())
                    .picture(emailDTO.getPicture())
                    .build();
            return EmailDao.save(email);
        }
        return null;
    }

    @Override
    public Email getUserById(long id) throws DataNotFoundException {
        return this.EmailDao.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Can not find email with id: " + id));

    }

    @Override
    public List<Email> getAllUsers() {
        return this.EmailDao.findAll();
    }

    @Override
    public void deleteCoupon(long id) {

    }

    @Override
    public Email getUserByEmail(String email) {
        return this.EmailDao.findUserByEmail(email);

    }

}
