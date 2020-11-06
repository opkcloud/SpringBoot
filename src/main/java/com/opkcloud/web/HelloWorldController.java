package com.opkcloud.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.opkcloud.pojo.User;

import io.swagger.annotations.ApiOperation;

/**
 * 
* Title: HelloWorldController
* Description: springboot 接口测试
* 首先启动 Application 程序，然后在浏览器输入http://localhost:8080//hello 
* 即可查看信息
* 在类中添加  @RestController, 默认类中的方法都会以json的格式返回
* Version:1.0.0  
* @author opkcloud
* @date 2018年1月3日
 */
@RestController
public class HelloWorldController {
	
	@ApiOperation(value = "新增用户信息", httpMethod = "GET", notes = "新增用户信息")
    @RequestMapping("/hello")
    public String index() {
    	System.out.println("---------开始----------");
        return "Hello World";
    }
    
	@ApiOperation(value = "新增用户信息", httpMethod = "GET", notes = "新增用户信息")
    @RequestMapping("/getUser")
    public User getUser() {
    	System.out.println("---------开始----------");
    	User user=new User();
    	user.setId(2);
    	user.setName("李四");
        return user;
    }
    
    
}
