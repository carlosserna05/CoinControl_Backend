package com.example.demo.repositories;

import com.example.demo.entities.Authority;
import com.example.demo.entities.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority,Long> {


    public Authority findByName(AuthorityName name);
}
