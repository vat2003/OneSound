package com.project.shopapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.shopapp.Service.EmailService;
import com.project.shopapp.entity.Email;
import com.project.shopapp.security.DataNotFoundException;
import com.project.shopapp.utils.CheckSocialAccountResponse;

/**
 * EmailController
 */

@RestController
@RequestMapping("${api.prefix}/emails")
@CrossOrigin(origins = "*")
public class EmailController {

    @Autowired
    EmailService EmailService;

    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUesrs(
            @PathVariable("id") Long id) throws DataNotFoundException {
        Email emailResponse = this.EmailService.getUserById(id);
        return ResponseEntity.ok(emailResponse);
    }

    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(
            @RequestParam("email") String email) throws DataNotFoundException {
        if (this.EmailService.getUserByEmail(email).getId() <= 0) {
            return ResponseEntity.ok(
                    CheckSocialAccountResponse.builder()
                            .message("failed")
                            .build());
        }
        return ResponseEntity.ok(
                CheckSocialAccountResponse.builder()
                        .message("successfully")
                        .quantity(this.EmailService.getAllUsers().size())
                        .build());
    }
}