package com.hs.doc.gen.test;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import com.hs.cart.proto.CartServiceProto.CartRequest;
import com.hs.doc.gen.processor.IDLObjectProcessor;

public class IDLObjectProcessorTest1 {
	public static void main(String [] args) throws Exception {
		Map<String , Object> attrMap = new HashMap<>();
		attrMap.put("userId-long", 1111);
		attrMap.put("data-java.lang.String", "这是一个测试");
		
		Map<String , Object> test0Map = new HashMap<>();
		test0Map.put("p0-long" , 123244);
		test0Map.put("p1-java.lang.String" , "测试123");
		
		Map<String , Object> testMap = new HashMap<>();
		testMap.put("index-long" , 77777);
		testMap.put("test0-com.hs.cart.proto.CartServiceProto$Test0$Builder" , test0Map);
		
		attrMap.put("test-com.hs.cart.proto.CartServiceProto$Test$Builder" , testMap);

		IDLObjectProcessor processor = new IDLObjectProcessor();
		Object object = processor.createAndInitializeObject("com.hs.cart.proto.CartServiceProto$CartRequest$Builder", attrMap);
		
		Method met = object.getClass().getDeclaredMethod("build");
		Object returnObj = met.invoke(object);
		Method lastMethod = returnObj.getClass().getMethod("toByteArray");
		byte [] data = (byte[]) lastMethod.invoke(returnObj);
		
		// 反序列化验证
		CartRequest verify = CartRequest.parseFrom(data);
		System.out.println(verify.getUserId() + " " + verify.getData() + " " + verify.getTest().getIndex());
	}
}
