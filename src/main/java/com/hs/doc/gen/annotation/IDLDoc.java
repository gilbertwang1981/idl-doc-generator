package com.hs.doc.gen.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface IDLDoc {
	
	/**
	 * 接口描述
	 */
	String description() default "";
	
	/**
	 * 接口版本号
	 */
	String version() default "";
}
