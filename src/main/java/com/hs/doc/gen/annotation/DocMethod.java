package com.hs.doc.gen.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.hs.doc.gen.consts.DocRequestMethod;

/**
 * 在线文档生成器注解
 * 
 * @author Gilbert Wang
 *
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface DocMethod {
	/**
	 * @return 对外接口名，比如/address/getUserAddress
	 */
	String name();
	
	/**
	 * @return 请求方式，比如GET/POST etc.
	 */
	DocRequestMethod [] method();
	
	/**
	 * @return MIME类型，比如：application/x-protobuf或application/json
	 */
	String[] produces();
	
	/**
	 * @return 方法详细描述
	 */
	String desc();
	
	/**
	 * @return 参数接口定义文件，文件名字，比如XXXX.proto
	 */
	String parameterTypeIdl();
	
	/**
	 * @return 返回值接口定义文件，文件名字，比如XXXX.proto
	 */
	String returnTypeIdl();
	
	/**
	 * @return 方法版本
	 */
	String version() default "1.0";
	
	/**
	 * @return 输入参数类，比如XXXX.class
	 */
	Class<?> parameterType();
	
	/**
	 * @return 输出参数类，比如XXXX.class
	 */
	Class<?> returnType();
}
