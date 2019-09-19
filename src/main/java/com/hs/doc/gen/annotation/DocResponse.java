package com.hs.doc.gen.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface DocResponse {
	/**
	 * 对应的idl文件名字
	 */
	String idl();
	
	/**
	 * idl版本
	 */
	String version() default "";
}
