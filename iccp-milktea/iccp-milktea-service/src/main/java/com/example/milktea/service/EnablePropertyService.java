package com.example.milktea.service;

import com.example.milktea.mapper.EnablePropertyMapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

import java.lang.annotation.*;

/**
 * @Description: 容器注入service
 * @Author: yaos
 * @Date: 2019-11-29 09:50
 * @Version: V1.0
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnablePropertyMapper
@ComponentScans(value={
	       @ComponentScan(basePackages="com.example.milktea.service"),
	        })
public @interface EnablePropertyService {

}
