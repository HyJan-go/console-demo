package com.example.consulribbonservice.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RetryRule;
import com.netflix.loadbalancer.RoundRobinRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @program: demo
 * @description: 客户端负载均衡配置，只要在restTemplate注入的时候，多加一个注解就可以了
 * 还可以指定负载的方式
 * @author: HyJan
 * @create: 2020-05-06 15:13
 **/
@Configuration
public class RibbonConfig {

    /**
     *  @LoadBalanced 这个注解不能少，不然就没有开启负载均衡
     *  在rest里面加，在使用这个rest进行调用的时候，就会在客户端根据对应的客户端方法或者是策略进行负载均衡
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    /**
     * java 配置方式来设置负载均衡的方式
     * 可以通过查看源码这个接口的实现方式有几种，然后选择，或者是自己集成然后写一个自己的负载方式
     * @return
     */
    @Bean
    public IRule rule(){
//        return new RandomRule();
//        return new RetryRule();
        return new RoundRobinRule();
    }
}
