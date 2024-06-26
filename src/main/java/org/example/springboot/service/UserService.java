package org.example.springboot.service;

import jakarta.annotation.Resource;
import org.example.springboot.dao.UserDao;
import org.example.springboot.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public List<User> findAll() {
        return userDao.selectAll();
    }

    public User login(User user) {
        return userDao.selectOne(user);
    }
}
