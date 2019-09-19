package com.hs.doc.gen.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import com.hs.doc.gen.consts.DocRequestMethod;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface DocMethod {
	/**
	 * 对外接口名，比如/address/getUserAddress
	 */
	String name();
	
	/**
	 * 请求方式
	 */
	DocRequestMethod [] method();
	
	/**
	 * MIME类型，比如：application/x-protobuf或application/json
	 */
	String[] produces();
	
	/**
	 * 方法详细描述
	 */
	String desc();
	
	/**
	 * 接口定义文件，文件名字
	 */
	String idl();
	
	/**
	 * 方法版本
	 */
	String version() default "";
}
