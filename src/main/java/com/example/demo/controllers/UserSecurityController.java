package com.example.demo.controllers;

import com.example.demo.dtos.DTOToken;
import com.example.demo.dtos.UserSecurityDTO;
import com.example.demo.entities.UserSecurity;
import com.example.demo.security.JwtUtilService;
import com.example.demo.security.SecurityUser;
import com.example.demo.services.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "**")
public class UserSecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private JwtUtilService jwtUtilService;
    @Autowired
    UserSecurityService userSecurityService;

    @PostMapping("/users")
    public ResponseEntity<UserSecurity> createUser(@RequestBody UserSecurityDTO user) {
        UserSecurity newUser = userSecurityService.register(user);
        return new ResponseEntity<UserSecurity>(newUser, HttpStatus.CREATED);
    }



    @PutMapping("/users")
    public ResponseEntity<UserSecurity> updateUser(@RequestBody UserSecurityDTO user) {
        UserSecurity newUser = userSecurityService.changePassword(user);
        return new ResponseEntity<UserSecurity>(newUser, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserSecurity> getUserById(@PathVariable("id") Long id) {
        UserSecurity user = userSecurityService.findById(id);
        return new ResponseEntity<UserSecurity>(user, HttpStatus.OK);
    }

    @PostMapping("/users/login")
    public ResponseEntity<DTOToken> authenticate(@RequestBody UserSecurityDTO user) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUserName(),
                        user.getPassword()));
        SecurityUser securityUser = (SecurityUser) this.userDetailsService.loadUserByUsername(
                user.getUserName());
        String jwt = jwtUtilService.generateToken(securityUser);
        Long id = securityUser.getUser().getId();
        return new ResponseEntity<DTOToken>(new DTOToken(jwt, id, user.getType()), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserSecurity>> getAllLocal() {
        List<UserSecurity> userSecurities = userSecurityService.listAll();
        return new ResponseEntity<List<UserSecurity>>(userSecurities, HttpStatus.OK);
    }
}
