package com.example.taxetnb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.taxetnb.entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    public User findByCin(String cin);
}
