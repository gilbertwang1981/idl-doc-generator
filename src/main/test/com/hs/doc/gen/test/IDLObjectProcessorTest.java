package com.hs.doc.gen.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hs.cart.proto.CartServiceProto.CartRequest;
import com.hs.cart.proto.CartServiceProto.Test0;
import com.hs.doc.gen.processor.IDLObjectProcessor;

public class IDLObjectProcessorTest {
	public static void main(String [] args) throws Exception {
		Map<String , Object> attrMap = new HashMap<>();
		attrMap.put("userId-long", 1111);
		attrMap.put("data-java.lang.String", "这是一个测试");
		
		Map<String , Object> test0Map = new HashMap<>();
		test0Map.put("p0-long" , 123244);
		test0Map.put("p1-java.lang.String" , "测试123");
		
		List<Object> test0List = new ArrayList<>();
		for (int i = 0;i < 10;i ++) {
			Map<String , Object> t = new HashMap<>();
			t.put("p0-long" , 123244 + i);
			t.put("p1-java.lang.String" , "测试123" + i);
			
			test0List.add(t);
		}
		
		Map<String , Object> testMap = new HashMap<>();
		testMap.put("index-long" , 77777);
		testMap.put("test0-com.hs.cart.proto.CartServiceProto$Test0$Builder" , test0Map);
		testMap.put("tests0-java.util.List-com.hs.cart.proto.CartServiceProto$Test0$Builder", test0List);
		
		attrMap.put("test-com.hs.cart.proto.CartServiceProto$Test$Builder" , testMap);
		
		attrMap.put("ts0-java.util.List-com.hs.cart.proto.CartServiceProto$Test0$Builder" , test0List);
		
		attrMap.put("et-enum-com.hs.cart.proto.CartServiceProto$CartRequest$Builder" , 0);
		
		IDLObjectProcessor processor = new IDLObjectProcessor();
		Object object = processor.createAndInitializeObject("com.hs.cart.proto.CartServiceProto$CartRequest$Builder", attrMap);
		
		Method met = object.getClass().getDeclaredMethod("build");
		Object returnObj = met.invoke(object);
		Method lastMethod = returnObj.getClass().getMethod("toByteArray");
		byte [] data = (byte[]) lastMethod.invoke(returnObj);
		
		// 反序列化验证
		CartRequest verify = CartRequest.parseFrom(data);
		System.out.println(verify.getUserId() + " " + verify.getData() + " " + 
				verify.getTest().getIndex() + " " + verify.getEtValue());
		List<Test0> tests0 = verify.getTest().getTests0List();
		for (Test0 test0 : tests0) {
			System.out.println(test0.getP0() + " " + test0.getP1());
		}
		
		System.out.println("####################");
		
		List<Test0> ts0 = verify.getTs0List();
		for (Test0 t0 : ts0) {
			System.out.println(t0.getP0() + " " + t0.getP1());
		}
	}
}
