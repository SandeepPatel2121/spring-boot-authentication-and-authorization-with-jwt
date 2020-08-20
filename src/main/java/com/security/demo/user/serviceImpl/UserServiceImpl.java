package com.security.demo.user.serviceImpl;

import com.security.demo.entity.User;
import com.security.demo.model.UserModel;
import com.security.demo.user.dao.UserDao;
import com.security.demo.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id) {
        userDao.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id).get();
    }

    @Override
    public User save(UserModel userModel) {
        User user = new User();
        
        user.setUsername(userModel.getUsername());
        user.setPassword(bcryptEncoder.encode(userModel.getPassword()));
        user.setAge(userModel.getAge());
        user.setSalary(userModel.getSalary());
        
        return userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
