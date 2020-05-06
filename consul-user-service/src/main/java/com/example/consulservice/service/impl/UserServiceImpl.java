package com.example.consulservice.service.impl;

import com.example.consulservice.domain.User;
import com.example.consulservice.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: demo
 * @description: 用户服务具体实现
 * @author: HyJan
 * @create: 2020-05-06 13:47
 **/
@Service
public class UserServiceImpl implements UserService {

    private List<User> users;

    @PostConstruct
    public void init() {
        users = new ArrayList<>();
        users.add(new User(115642L, "John", "12345678"));
        users.add(new User(115645L, "Lily", "12345678"));
        users.add(new User(1156478L, "Jackson", "12345678"));
    }

    @Override
    public void createUser(User user) {
        users.add(user);
    }

    @Override
    public User getUser(Long id) {
        List<User> userList = users.stream().filter(user -> user.getId().equals(id))
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(userList)) {
            return userList.get(0);
        }
        return null;
    }

    @Override
    public void updateUser(User user) {
        // 找到一样的然后再重新赋值，然后插入数据库，这里没有插入数据库这步操作
        users.stream().filter(user1 -> user1.getId().equals(user.getId()))
                .forEach(userItem -> {
//                    userItem.setId(user.getId());
//                    userItem.setUsername(user.getUsername());
//                    userItem.setPassword(user.getPassword());
                    // 可以使用spring提供的utils进行拷贝
                    BeanUtils.copyProperties(user, userItem);
                });
    }

    @Override
    public void deleteUser(Long id) {
        User user = getUser(id);
        if (user != null) {
            users.remove(user);
        }
    }

    @Override
    public User getByUsername(String username) {
        List<User> collect = users.stream().filter(user -> user.getUsername().equals(username))
                .collect(Collectors.toList());
        if (!CollectionUtils.isEmpty(collect)) {
            return collect.get(0);
        }
        return null;
    }

    @Override
    public List<User> getUserByIds(List<Long> ids) {
        return users.stream().filter(user -> ids.contains(user.getId()))
                .collect(Collectors.toList());
    }
}
