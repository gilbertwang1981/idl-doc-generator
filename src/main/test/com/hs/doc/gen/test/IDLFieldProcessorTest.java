package com.hs.doc.gen.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.protobuf.Message.Builder;
import com.googlecode.protobuf.format.JsonFormat;
import com.hs.cart.proto.CartServiceProto.CartRequest;
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

class MiddleObject {
	private Long index;
	private SimpleObject test0;
	private List<SimpleObject> tests0;
	public Long getIndex() {
		return index;
	}
	public void setIndex(Long index) {
		this.index = index;
	}
	public SimpleObject getTest0() {
		return test0;
	}
	public void setTest0(SimpleObject test0) {
		this.test0 = test0;
	}
	public List<SimpleObject> getTests0() {
		return tests0;
	}
	public void setTests0(List<SimpleObject> tests0) {
		this.tests0 = tests0;
	}
}

class UpperObject {
	private Long userId;
	private String data;
	private MiddleObject test;
	private List<SimpleObject> ts0;
	private int et;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public MiddleObject getTest() {
		return test;
	}
	public void setTest(MiddleObject test) {
		this.test = test;
	}
	public List<SimpleObject> getTs0() {
		return ts0;
	}
	public void setTs0(List<SimpleObject> ts0) {
		this.ts0 = ts0;
	}
	public int getEt() {
		return et;
	}
	public void setEt(int et) {
		this.et = et;
	}
}

public class IDLFieldProcessorTest {
	public static void main(String [] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		SimpleObject simple = new SimpleObject();
		simple.setP0(11111L);
		simple.setP1("hello world,12434");
		
		List<SimpleObject> simples = new ArrayList<>();
		simples.add(simple);
		
		MiddleObject middle = new MiddleObject();
		middle.setIndex(4567L);
		middle.setTest0(simple);
		middle.setTests0(simples);
		
		UpperObject upper = new UpperObject();
		upper.setData("你好");
		upper.setEt(1);
		upper.setUserId(564343L);
		upper.setTest(middle);
		upper.setTs0(simples);
		
		String jsonString = new Gson().toJson(upper);
		
		Class<?> clazz = Class.forName("com.hs.cart.proto.CartServiceProto$CartRequest");
		Method method = clazz.getMethod("newBuilder");

		Builder builder = (Builder) method.invoke(null);
		new JsonFormat().merge(new ByteArrayInputStream(jsonString.getBytes()) ,  builder);
		
		CartRequest.Builder t0 = (CartRequest.Builder)builder;
		System.out.println(t0.getData() + "/" + t0.getEtValue() + "/" + t0.getUserId());
		for (Test0 test0 : t0.getTs0List()) {
			System.out.println(test0.getP0() + "/" + test0.getP1());
		}
		System.out.println(t0.getTest().getIndex());
	}
}
