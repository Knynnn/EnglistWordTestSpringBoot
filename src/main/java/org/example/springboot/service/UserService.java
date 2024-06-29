package org.example.springboot.service;

import jakarta.annotation.Resource;
import org.example.springboot.dao.UserDao;
import org.example.springboot.entity.User;
import org.example.springboot.exception.CustomException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Resource
    private UserDao userDao;

    public List<User> findAll() {
        return userDao.selectAll();
    }

    public User add(User user) {
        if(user.getId() == null){
            throw new CustomException("用户学号不能为空");
        }
        if(user.getPassword() == null || "".equals(user.getPassword())){
            throw new CustomException("用户名密码不能为空");
        }
        User u = userDao.findById(user.getId());
        if (u != null) {
            throw new CustomException("该学号已存在,请勿重复添加");
        }
        userDao.insertSelective(user);
        return u;
    }

    public User login(User user) {
        if(user.getId() == null){
            throw new CustomException("用户学号不能为空");
        }
        if(user.getPassword() == null || "".equals(user.getPassword())){
            throw new CustomException("用户密码不能为空");
        }
        User u = userDao.findByIdAndPassword(user.getId(),user.getPassword());
        if(u == null) {
            throw new CustomException("用户名学号或密码错误");
        }
        return u;
    }

    public User findUserById(Integer userId) {
        User u = userDao.findById(userId);
        if(u == null) {
            throw new CustomException("用户不存在");
        }
        return u;
    }

    public void updateUser(User user) {
        userDao.updateByPrimaryKeySelective(user);
    }
}
