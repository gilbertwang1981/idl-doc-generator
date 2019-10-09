package com.hs.doc.gen.test;

import com.google.protobuf.Message.Builder;
import com.hs.cart.proto.CartServiceProto.CartRequest;
import com.hs.cart.proto.CartServiceProto.Test0;
import com.hs.doc.gen.deserialize.IDLObjectDeserializer;

public class IDLFieldProcessorTest {
	public static void main(String [] args) throws Exception {
		String jsonString = "{\"userId\":564343,\"data\":\"你好\",\"test\":"
				+ "{\"index\":4567,\"test0\":{\"p0\":11111,\"p1\":\"hello world,12434\"},"
				+ "\"tests0\":[{\"p0\":11111,\"p1\":\"hello world,11111\"},{\"p0\":2222,\"p1\":\"hello world,22222\"}]},"
				+ "\"ts0\":[{\"p0\":11111,\"p1\":\"hello world,12434\"}],\"et\":1}";

		// 转换
		Builder builder = new IDLObjectDeserializer().deserialize("com.hs.cart.proto.CartServiceProto.CartRequest", jsonString);
		
		byte [] data = builder.build().toByteArray();
		System.out.println("数据长度：" + data.length);
		
		// 验证
		CartRequest.Builder vbuilder = (CartRequest.Builder)builder;
		System.out.println(vbuilder.getData() + "/" + vbuilder.getEtValue() + "/" + vbuilder.getUserId());
		for (Test0 test0 : vbuilder.getTs0List()) {
			System.out.println(test0.getP0() + "/" + test0.getP1());
		}
		
		for (Test0 test0 : vbuilder.getTest().getTests0List()) {
			System.out.println(test0.getP0() + "/" + test0.getP1());
		}
		
		System.out.println(vbuilder.getTest().getIndex());
	}
}
