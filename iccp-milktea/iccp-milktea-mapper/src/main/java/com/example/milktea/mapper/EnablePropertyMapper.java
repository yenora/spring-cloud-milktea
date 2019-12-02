package com.example.milktea.mapper;

import org.mybatis.spring.annotation.MapperScan;

import java.lang.annotation.*;

/**
 * @Description: 容器注入mapper
 * @Author: yaos
 * @Date: 2019-11-29 09:50
 * @Version: V1.0
 **/
@Target({ ElementType.TYPE, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MapperScan("com.example.milktea.mapper")
public @interface EnablePropertyMapper {

}
