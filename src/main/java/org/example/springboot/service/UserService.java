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

    public void add(User user) {
        if(user.getId() == null){
            throw new CustomException("用户学号不能为空");
        }
        if(user.getName() == null || "".equals(user.getName())){
            throw new CustomException("用户名不能为空");
        }
        User u = userDao.findById(user.getId());
        if (u != null) {
            throw new CustomException("该学号已存在,请勿重复添加");
        }
        userDao.insertSelective(user);
    }

    public User login(User user) {
//        if(user.getId() == null){
//            throw new CustomException("用户学号不能为空");
//        }
        if(user.getPassword() == null){
            throw new CustomException("用户密码不能为空");
        }
        User u = userDao.findByIdAndPassword(user.getId(),user.getPassword());
        if(u == null) {
            throw new CustomException("用户名或密码错误");
        }
        return u;
    }

}
