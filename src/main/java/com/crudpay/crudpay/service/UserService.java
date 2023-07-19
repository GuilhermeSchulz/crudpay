package com.crudpay.crudpay.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crudpay.crudpay.model.User;
import com.crudpay.crudpay.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public User createUser(final User userData){
        final User user = new User(userData.getName(), userData.getCpf(), userData.getEmail(), userData.getPassword(), userData.getType());

        return userRepository.save(user);
    }

    public List<User> readUsers(){
        return userRepository.findAll();
    }

}

