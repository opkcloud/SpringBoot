package com.opkcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * url: http://localhost:8083/swagger-ui.html#/
 * SSM架构: https://www.cnblogs.com/zyw-205520/p/4771253.html
 * Java反射:
 * RestTemplate: https://blog.csdn.net/yiifaa/article/details/77939282
 */
@SpringBootApplication
public class Application {

    public static void main( String[] args ) {
    	// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
		SpringApplication.run(Application.class, args);
		System.out.println("程序正在运行...");
    }
    
}
