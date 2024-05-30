package com.example.demo.repositories;

import com.example.demo.entities.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserSecurityRepository extends JpaRepository<UserSecurity, Long> {

    public UserSecurity findByUserName(String userName);

}
