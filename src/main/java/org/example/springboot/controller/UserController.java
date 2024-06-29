package org.example.springboot.controller;

import jakarta.annotation.Resource;
import org.example.springboot.common.Result;
import org.example.springboot.entity.KaoyanWord;
import org.example.springboot.entity.User;
import org.example.springboot.service.UserService;
import org.example.springboot.util.JwtUtil;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        User u = userService.login(user);
        if(u != null) {
            String token = JwtUtil.generateToken(u.getId().toString());
            Map<String, Object> data = new HashMap<>();
            data.put("user", u);
            data.put("token", token);
            return Result.success(data);
        } else {
            return Result.error("用户名或密码错误");
        }
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        userService.add(user);
        return Result.success();
    }

    @GetMapping("/info/{id}")
    public Result getUserInfo(@PathVariable Integer id) {
        User u = userService.findUserById(id);
        if (u != null) {
            return Result.success(u);
        } else {
            return Result.error("用户信息获取失败");
        }
    }

    @PostMapping("/update")
    public Result updateUserInfo(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success();
    }

    @PostMapping("/updateWordSize")
    public Result updateWordSize(@RequestBody User user) {
        User u = userService.findUserById(user.getId());
        if (u != null) {
            u.setWordsize(user.getWordsize());
            userService.updateUser(u);
            return Result.success();
        } else {
            return Result.error("用户信息获取失败");
        }
    }
}
