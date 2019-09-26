package com.hs.doc.gen.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.hs.doc.gen.processor.IDLObjectProcessor;

public class IDLObjectProcessorTest0 {
	public static void main(String [] args) throws Exception {
		Map<String , Object> attrMap = new HashMap<>();
		attrMap.put("companyCode-java.lang.String", "001");
		
		Map<String , Object> subAttrMap = new HashMap<>();
		subAttrMap.put("name-java.lang.String" , "王晗");
		subAttrMap.put("age-java.lang.Integer" , 32);
		subAttrMap.put("sex-int" , 10);
		subAttrMap.put("userId-long" , 100011);
		
		attrMap.put("monitor-com.hs.doc.gen.test.Employee", subAttrMap);
		
		List<Map<String , Object>> multi = new ArrayList<>();
		for (int i = 0;i < 10; i ++) {
			Map<String , Object> attr = new HashMap<>();
			attr.put("name-java.lang.String" , "王晗" + i);
			attr.put("age-java.lang.Integer" , 32 + i);
			attr.put("sex-int" , 10 + i);
			attr.put("userId-long" , 100011 + i);
			
			multi.add(attr);
		}
		attrMap.put("employee-java.util.List-com.hs.doc.gen.test.Employee" , multi);
		
		IDLObjectProcessor processor = new IDLObjectProcessor();
		Object object = processor.createAndInitializeObject("com.hs.doc.gen.test.Company", attrMap);
		System.out.println(new Gson().toJson(object));
	}
}
