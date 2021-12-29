package com.rion.blog.service;

import javax.transaction.Transactional;

import com.rion.blog.model.User;
import com.rion.blog.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
 
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public int join(User user) {
        try {
            userRepository.save(user);
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("UserService : 회원가입() : " + e.getMessage());
        }

        return -1;
    }
}
