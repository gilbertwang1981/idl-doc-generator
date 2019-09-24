package com.hs.doc.gen.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface DocService {
	/**
	 * 服务名
	 */
	String service();
	
	/**
	 * 模块名
	 */
	String module();
	
	/**
	 * 服务版本
	 */
	String version() default "";
	
	/**
	 *  模块地址
	 */
	String value();
}
