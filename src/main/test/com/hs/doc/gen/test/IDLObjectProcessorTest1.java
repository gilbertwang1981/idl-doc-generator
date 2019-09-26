package com.hs.doc.gen.test;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.hs.doc.gen.processor.IDLObjectProcessor;

public class IDLObjectProcessorTest1 {
	public static void main(String [] args) throws Exception {
		Map<String , Object> attrMap = new HashMap<>();
		attrMap.put("channelUserId-java.lang.String", "gilbert.wang");
		attrMap.put("channelId-long", 123345);
		
		IDLObjectProcessor processor = new IDLObjectProcessor();
		Object object = processor.createAndInitializeObject("com.hs.user.base.proto.UserBaseServiceProto$UserInfoRequest$Builder", attrMap);
		System.out.println(new Gson().toJson(object));
	}
}
