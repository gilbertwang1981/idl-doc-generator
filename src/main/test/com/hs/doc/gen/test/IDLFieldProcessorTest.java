package com.hs.doc.gen.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.google.gson.Gson;
import com.google.protobuf.Message.Builder;
import com.googlecode.protobuf.format.JsonFormat;
import com.hs.cart.proto.CartServiceProto.Test0;

class SimpleObject {
	private Long p0;
	private String p1;
	
	public Long getP0() {
		return p0;
	}
	public void setP0(Long p0) {
		this.p0 = p0;
	}
	public String getP1() {
		return p1;
	}
	public void setP1(String p1) {
		this.p1 = p1;
	}
}

public class IDLFieldProcessorTest {
	public static void main(String [] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		SimpleObject test = new SimpleObject();
		test.setP0(11111L);
		test.setP1("hello world,12434");
		
		String jsonString = new Gson().toJson(test);
		
		Class<?> clazz = Class.forName("com.hs.cart.proto.CartServiceProto$Test0");
		Method method = clazz.getMethod("newBuilder");

		Builder builder = (Builder) method.invoke(null);
		new JsonFormat().merge(new ByteArrayInputStream(jsonString.getBytes()) ,  builder);
		
		Test0.Builder t0 = (Test0.Builder)builder;
		System.out.println(t0.getP0() + "/" + t0.getP1());
	}
}
