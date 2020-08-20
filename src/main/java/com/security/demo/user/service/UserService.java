package com.security.demo.user.service;

import com.security.demo.entity.User;
import com.security.demo.model.UserModel;

import java.util.List;

public interface UserService {

    User save(UserModel user);

    List<User> findAll();

    void delete(long id);

    User findById(Long id);

    public User findByUsername(String username);
}
