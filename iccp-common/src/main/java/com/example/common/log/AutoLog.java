package com.example.common.log;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 日志输入注解
 * @Author: yaos
 * @Date: 2019-11-29 09:50
 * @Version: V1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AutoLog {

	/**
	 * 日志内容
	 * @return
	 */
	String value() default "";
	
	boolean request() default  true;
	
	boolean response() default  true;
}
