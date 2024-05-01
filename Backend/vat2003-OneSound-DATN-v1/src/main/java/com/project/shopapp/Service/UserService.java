package com.project.shopapp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.shopapp.entity.Account;
import com.project.shopapp.repository.AccountDAO;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	AccountDAO dao;
	
	@Autowired
    public UserService(AccountDAO AccountDAO) {
        this.dao = AccountDAO;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account user = dao.findByEmail(email).get();
        CustomUserDetails userPrincaple = new CustomUserDetails(user);
        return userPrincaple;
    }

    public boolean ifEmailExist(String mail){
        return dao.existsByEmail(mail);
    }

    public Account getUserByMail(String mail){
        return dao.findByEmail(mail).get();
    }

    public Account saveUser(Account user){
        return dao.save(user);
    }
}
