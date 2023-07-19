package com.crudpay.crudpay.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crudpay.crudpay.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
}
