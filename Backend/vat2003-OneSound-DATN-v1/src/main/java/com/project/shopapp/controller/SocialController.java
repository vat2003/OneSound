package com.project.shopapp.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Value;
import com.project.shopapp.Service.TokenService;
import com.project.shopapp.Service.UserService;
import com.project.shopapp.dto.JWTLogin;
import com.project.shopapp.dto.LoginResponse;
import com.project.shopapp.dto.TokenDTO;
import com.project.shopapp.entity.Account;
import com.project.shopapp.entity.Role;
import com.project.shopapp.repository.AccountDAO;
import com.project.shopapp.repository.RoleDAO;

@RestController
@RequestMapping("/social")
@CrossOrigin("http://localhost:4200")
public class SocialController {

    private UserService service;
    @Autowired
    private RoleDAO roledao;

    @Autowired
    private AccountDAO dao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    private String email;

    @Autowired
    private TokenService tokenService;

    @Value("${google.id}")
    private String idClient;

    @Value("${mySecret.password}")
    private String password;

    @PostMapping("/google")
    public ResponseEntity<LoginResponse> loginWithGoogle(@RequestBody TokenDTO tokenDto) throws Exception {
        System.out.println("pass " + password);
        NetHttpTransport transport = new NetHttpTransport();
        JacksonFactory factory = JacksonFactory.getDefaultInstance();
        GoogleIdTokenVerifier.Builder ver = new GoogleIdTokenVerifier.Builder(transport, factory)
                .setAudience(Collections.singleton(idClient));
        GoogleIdToken googleIdToken = GoogleIdToken.parse(ver.getJsonFactory(), tokenDto.getToken());
        GoogleIdToken.Payload payload = googleIdToken.getPayload();
        email = payload.getEmail();
        Account user = new Account();
        if (dao.existsByEmail(email)) {
            user = dao.findByEmail(email).get();
        } else {
            user = createUser(email);
        }
        ///////////////////////////
        JWTLogin jwtLogin = new JWTLogin();
        jwtLogin.setEmail(user.getEmail());
        jwtLogin.setPassword(password);
        ///////////////////////////

        return new ResponseEntity<LoginResponse>(tokenService.login(jwtLogin), HttpStatus.OK);
    }

    private Account createUser(String email) {
        Account user = new Account();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        Role roles = roledao.findById(1L).get();
        user.setAccountRole(roles);
        return dao.save(user);
    }

    // http://localhost:8080/social/facebook
    @PostMapping("/facebook")
    public ResponseEntity<LoginResponse> loginWithFacebook(@RequestBody TokenDTO tokenDto) throws Exception {
        Facebook facebook = new FacebookTemplate(tokenDto.getToken());
        String[] data = { "email" };
        org.springframework.social.facebook.api.User user = facebook.fetchObject("me",
                org.springframework.social.facebook.api.User.class, data);

        email = user.getEmail();
        Account userFace = new Account();
        if (dao.existsByEmail(email)) {
            userFace = dao.findByEmail(email).get();
        } else {
            userFace = createUser(email);
        }
        ///////////////////////////
        JWTLogin jwtLogin = new JWTLogin();
        jwtLogin.setEmail(user.getEmail());
        jwtLogin.setPassword(password);
        ///////////////////////////

        return new ResponseEntity<LoginResponse>(tokenService.login(jwtLogin), HttpStatus.OK);
    }
}
