package com.example.milktea.rest;

import com.example.milktea.service.EnablePropertyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Description:
 * @Author: yaos
 * @Date: 2019-12-02 11:28
 * @Version: V1.0
 **/
@SpringBootApplication
@EnableAspectJAutoProxy(exposeProxy = true) // 开启AOP切入
@EnableEurekaClient
@EnablePropertyService
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
