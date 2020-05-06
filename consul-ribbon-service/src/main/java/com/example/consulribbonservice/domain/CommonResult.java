package com.example.consulribbonservice.domain;

import lombok.Data;

/**
 * @program: demo
 * @description: 建立一个泛型的返回类，作为通用类进行返回信息的结果类
 *  一般要包含的信息就是结果码，然后就是出错消息，还有就是返回的数据
 * @author: HyJan
 * @create: 2020-05-06 11:43
 **/
@Data
public class CommonResult<T> {
    private T data;
    private Integer code;
    private String msg;

    public CommonResult(){

    }

    public CommonResult(T data,Integer code,String msg){
        this.data = data;
        this.code = code;
        this.msg = msg;
    }

    /**
     * this 表示当前对象，可以直接当任意的构造函数来用
     * @param code
     * @param msg
     */
    public CommonResult(Integer code,String msg){
        this(null,code,msg);
    }

    public CommonResult(T data){
        this(data,200,"操作成功");
    }
}
