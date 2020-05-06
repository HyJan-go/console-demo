package com.example.consulservice.service;

import com.example.consulservice.domain.User;

import java.util.List;

/**
 * @program: demo
 * @description: 用户服务类
 * @author: HyJan
 * @create: 2020-05-06 12:01
 **/
public interface UserService {

    /**
     * 创建用户信息
     * @param user
     */
    void createUser(User user);

    /**
     * 通过id获取用户信息
     * @param id
     * @return
     */
    User getUser(Long id);

    /**
     * 更新用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除用户信息
     * @param id
     */
    void deleteUser(Long id);

    /**
     * 通过用户名获取用户信息
     * @param username
     * @return
     */
    User getByUsername(String username);

    /**
     * 通过id列表获取用户信息列表
     * @param ids
     * @return
     */
    List<User> getUserByIds(List<Long> ids);
}
