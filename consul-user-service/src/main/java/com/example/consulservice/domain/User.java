package com.example.consulservice.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: demo
 * @description: 用户实体类
 * @author: HyJan
 * @create: 2020-05-06 11:58
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;
    private String username;
    private String password;
}
