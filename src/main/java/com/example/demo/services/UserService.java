package com.example.demo.services;

import com.example.demo.entities.User;

import java.util.List;

public interface UserService {

    public List<User> listAll();
    public User save(User user);
    public void delete(Long id);
    public User findById(Long id);


}
