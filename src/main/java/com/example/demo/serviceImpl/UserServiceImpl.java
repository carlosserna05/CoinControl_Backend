package com.example.demo.serviceImpl;

import com.example.demo.entities.User;
import com.example.demo.exceptions.IncompleteDataException;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repositories.UserRepository;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public List<User> listAll() {
        List<User> users=userRepository.findAll();
        return users;
    }

    @Override
    public User save(User user) {

        if (user.getUsername()==null || user.getUsername().isEmpty()) {
            throw new IncompleteDataException("name can not be null or empty");
        }


        return userRepository.save(user);
    }

    @Override
    public void delete(Long id){
        User user=findById(id);
        userRepository.delete(user);
    }

    @Override
    public User findById(Long id){
        User userFound = userRepository.findById(id).orElse(null);
        if (userFound == null) {
            throw new ResourceNotFoundException("There are no object with the id: "+String.valueOf(id));
        }
        return userFound;
    }


}
