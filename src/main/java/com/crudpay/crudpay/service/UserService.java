package com.crudpay.crudpay.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.crudpay.crudpay.dto.CreateDepositDto;
import com.crudpay.crudpay.dto.UserDto;
import com.crudpay.crudpay.model.User;
import com.crudpay.crudpay.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(final UserDto userData) {
        final User user = new User(userData.getName(), userData.getCpf(), userData.getEmail(), userData.getPassword(),
                userData.getType());

        return userRepository.save(user);
    }

    public List<User> readUsers() {
        return userRepository.findAll();
    }

    public User readUser(final Long id) throws RuntimeException {
        final User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return user;
    }

    public User updateUser(final Long id, final UserDto userData) throws RuntimeException {
        final User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userData.getName());
        user.setCpf(userData.getCpf());
        user.setEmail(userData.getEmail());
        user.setPassword(userData.getPassword());
        user.setType(userData.getType());

        return userRepository.save(user);
    }

    public void deleteUser(final Long id) throws RuntimeException {
        final User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }

    public User addBalance(final long id, final CreateDepositDto depositData) throws RuntimeException {
        final User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        final float currentBalance = user.getBalance();
        user.setBalance(currentBalance + depositData.getValue());
        return userRepository.save(user);
    }
}
