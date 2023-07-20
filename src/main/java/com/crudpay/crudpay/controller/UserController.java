package com.crudpay.crudpay.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crudpay.crudpay.dto.CreateDepositDto;
import com.crudpay.crudpay.dto.UserDto;
import com.crudpay.crudpay.model.User;
import com.crudpay.crudpay.service.UserService;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody final UserDto userdata) {
        final User createdUser = userService.createUser(userdata);
        return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        final List<User> users = userService.readUsers();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")

    public ResponseEntity<User> getUserById(@PathVariable String id) throws Exception {
        final User user = userService.readUser(Long.parseLong(id));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PutMapping("/{id}")

    public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody UserDto userData) throws Exception {
        final User user = userService.updateUser(Long.parseLong(id), userData);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String id) throws Exception {
        userService.deleteUser(Long.parseLong(id));
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<User> createDeposit(@PathVariable String id, @RequestBody CreateDepositDto depositData)
            throws Exception {
        final User user = userService.addBalance(Long.parseLong(id), depositData);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
}
