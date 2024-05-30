package com.example.demo.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSecurityDTO {

    private Long id;

    private String userName;
    private String password;
    private String type;

}
