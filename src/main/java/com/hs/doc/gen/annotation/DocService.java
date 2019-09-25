package com.hs.doc.gen.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 在线文档生成器注解
 * 
 * @author Gilbert Wang
 *
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface DocService {
	/**
	 * @return 服务名，比如order-service
	 */
	String service();
	
	/**
	 * @return 模块名，比如订单售前模块
	 */
	String module();
	
	/**
	 * @return 服务版本
	 */
	String version() default "1.0";
	
	/**
	 *  @return 模块地址，比如/order
	 */
	String value();
}
