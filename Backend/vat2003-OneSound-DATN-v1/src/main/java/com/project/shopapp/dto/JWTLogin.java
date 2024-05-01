package com.project.shopapp.dto;

import lombok.Data;

@Data
public class JWTLogin {
	private String email;

    private String password;
}
