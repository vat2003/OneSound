package com.project.shopapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.project.shopapp.dto.JWTLogin;
import com.project.shopapp.dto.LoginResponse;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

import java.util.Date;


@Service
public class TokenService {
    private AuthenticationManager authenticationManager;

    @Autowired
    public TokenService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    private String generateToken(Authentication authResult) {
        // Grab principal
        CustomUserDetails principal = (CustomUserDetails) authResult.getPrincipal();
        System.out.println(principal.getUsername());

        // Create JWT Token
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 864_000_000)) // Example: 10 days
                .sign(HMAC512("your_secret_key".getBytes())); // Replace "your_secret_key" with your actual secret key
        return token;
    }

    public LoginResponse login(JWTLogin jwtLogin) throws Exception{
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtLogin.getEmail(),
                jwtLogin.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = generateToken(authenticate);
        return new LoginResponse(token);
    }
}
