package org.example.springboot.controller;

import jakarta.annotation.Resource;
import org.example.springboot.common.Result;
import org.example.springboot.entity.User;
import org.example.springboot.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    /**
     * controller的一个方法，他其实就是我们平时web项目的一个接口的入口
     * 可以在这个方法上再加上个url
     * 也可以指定请求方式：GET，POST，PUT，DELETE
     * @return
     */

    @GetMapping
    public Result findAll() {
        List<User> list = userService.findAll();
        return Result.success(list);
    }
}
