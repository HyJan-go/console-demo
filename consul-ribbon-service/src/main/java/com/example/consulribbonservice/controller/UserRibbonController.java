package com.example.consulribbonservice.controller;

import com.example.consulribbonservice.domain.CommonResult;
import com.example.consulribbonservice.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 * @program: demo
 * @description: 调用用户服务负载均衡控制类
 * 采用restTemplate进行http的远程服务调用
 * @author: HyJan
 * @create: 2020-05-06 15:23
 **/
@RestController
@RequestMapping("/user")
public class UserRibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${service-url.consul-user-service}")
    private String userServiceUrl;

    @GetMapping("/{id}")
    public CommonResult getUserById(@PathVariable Long id) {
        // 第一个参数是地址，第二个参数是返回值类型(就是在请求的方法中的返回值)
        return restTemplate.getForObject(new StringBuilder().append(userServiceUrl).append("/user/").append(id).toString(), CommonResult.class);
    }

    @GetMapping("/getByUsername")
    public CommonResult getByUsername(@RequestParam String username) {
        // 第一个参数是地址（用代替符进行顶位），第二个参数是返回值类型，第三个参数是替换替换符的值
        return restTemplate.getForObject(userServiceUrl + "/user/getByUsername?username={1}", CommonResult.class, username);
    }

    @GetMapping("/getEntityByUsername")
    public CommonResult getEntityByUsername(@RequestParam String username) {
        // 第一个参数是地址（用代替符进行顶位），第二个参数是返回值类型，第三个参数是替换替换符的值,这个方法的返回值是一个泛型的ResponseEntity
        ResponseEntity<CommonResult> entity = restTemplate.getForEntity(userServiceUrl + "/user/getByUsername?username={1}", CommonResult.class, username);
        // 判断是否成功
        if (entity.getStatusCode().is2xxSuccessful()){
            // 获取真的要的数据
            return entity.getBody();
        }else {
            return new CommonResult(500,"操作失败");
        }
    }

    @PostMapping("/create")
    public CommonResult createUser(@RequestBody User user){
        // post 请求，第一个参数是地址，第二个参数是请求的参数，第三个参数是返回值类型
        return restTemplate.postForObject(new StringBuilder().append(userServiceUrl).append("/user/create").toString(),user,CommonResult.class);
    }

    @PostMapping("/update")
    public CommonResult updateUser(@RequestBody User user){
        return restTemplate.postForObject(new StringBuilder().append(userServiceUrl).append("/user/update").toString(),user,CommonResult.class);
    }

    @PostMapping("/delete/{id}")
    public CommonResult deleteUser(@PathVariable Long id){
        // post请求，地址，参数是没有的，restful风格，用替代符的方式
        return restTemplate.postForObject(new StringBuilder().append("/user/delete/{1}").toString(),null,CommonResult.class,id);
    }
}
