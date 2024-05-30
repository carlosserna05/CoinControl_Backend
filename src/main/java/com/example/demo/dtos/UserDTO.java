package com.example.demo.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class UserDTO {


    private String username;
    private String password;
    private Date expirityDay;
    private Boolean is_active;
}
