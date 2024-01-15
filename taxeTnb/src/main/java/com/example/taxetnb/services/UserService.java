package com.example.taxetnb.services;

import com.example.taxetnb.dao.UserDao;
import com.example.taxetnb.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserDao userRepo;

    public List<User> findAll(){
        return userRepo.findAll();

    }
    public User findByCin(String cin) {
        return userRepo.findByCin(cin);
    }

    public Optional<User> findById(int id) {
        return userRepo.findById(id);
    }

    public int save(User user) {
    	User existingUser = findByCin(user.getCin());
        if (existingUser!=null) {
            return -1;
        }else {
        	userRepo.save(user);
            return 1;
        }
    }

    public void deleteById(int id) {
    	userRepo.deleteById(id);
    }
}
