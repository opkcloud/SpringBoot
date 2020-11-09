package com.opkcloud;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import java.text.MessageFormat;

/**
 * url: http://localhost:8083/swagger-ui.html#/
 * SSM架构: https://www.cnblogs.com/zyw-205520/p/4771253.html
 * Java反射:
 * RestTemplate: https://blog.csdn.net/yiifaa/article/details/77939282
 *
 * API 文档：https://blog.csdn.net/qq_38555490/article/details/98212295
 *
 */
@Slf4j
@SpringBootApplication
public class Application {

    public static void main( String[] args ) {
    	// 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        ConfigurableApplicationContext application = SpringApplication.run(Application.class, args);
        ConfigurableEnvironment env = application.getEnvironment();
        String ip = "";
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        String websocketPort = env.getProperty("websocket.port");
        String envStr = env.getProperty("spring.profiles.active");
        String formatMessage = MessageFormat.format(
                "\n------------------------------------------------------------------\n\t" +
                        "Backend management system is running ! Access URLs:\n\t" +
                        "Environment: \t{4}\n\t" +
                        "Local: \t\t\thttp://localhost:{0}{1}/\n\t" +
                        "External: \t\thttp://{2}:{0}{1}/\n\t" +
                        "WebSocket: \t\tws://{2}:{3}/websocket/\n\t" +
                        "Api doc: \t\thttp://{2}:{0}{1}/doc.html\n" +
                        "------------------------------------------------------------------",
                port, path, ip, websocketPort == null ? "" : "", envStr != null ? envStr.toUpperCase() : "");
        log.info(formatMessage);
    }
    
}
