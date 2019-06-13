package com.pancm;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

/**
 * url: http://localhost:8083/swagger-ui.html#/
 * https://www.cnblogs.com/zyw-205520/p/4771253.html
 */
@SpringBootApplication
public class App 
{
    public static void main( String[] args )
    {
    	// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(App.class, args);
		System.out.println("程序正在运行...");
    }
    
}
