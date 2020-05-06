package com.example.consulservice.controller;

import com.example.consulservice.domain.CommonResult;
import com.example.consulservice.domain.User;
import com.example.consulservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: demo
 * @description: 用户控制类
 * @author: HyJan
 * @create: 2020-05-06 14:29
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public CommonResult createUser(@RequestBody User user) {
        userService.createUser(user);
        return new CommonResult(200, "操作成功");
    }

    @GetMapping("/{id}")
    public CommonResult getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        log.info("获得的用户名称为: {}", user.getUsername());
        return new CommonResult(user);
    }

    @GetMapping("/getByUserIds")
    public CommonResult getUserByIds(@RequestParam List<Long> ids){
        List<User> users = userService.getUserByIds(ids);
        log.info("用户数 {}", users.size());
        return new CommonResult(users);
    }

    @GetMapping("/getByUsername")
    public CommonResult getUserByName(@RequestParam String username){
        User user = userService.getByUsername(username);
        return new CommonResult(user);
    }

    @PostMapping("/update")
    public CommonResult updateUser(@RequestBody User user){
        userService.updateUser(user);
        return new CommonResult(200,"操作成功");
    }

    @PostMapping("/delete/{id}")
    public CommonResult deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
        return new CommonResult(200,"操作成功");
    }
}
