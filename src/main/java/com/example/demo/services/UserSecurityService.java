package com.example.demo.services;

import com.example.demo.dtos.UserSecurityDTO;
import com.example.demo.entities.UserSecurity;

import java.util.List;

public interface UserSecurityService {

    public UserSecurity findById(Long id);

    public UserSecurity register(UserSecurityDTO user);

    public UserSecurity changePassword(UserSecurityDTO user);

    public List<UserSecurity> listAll();

}
