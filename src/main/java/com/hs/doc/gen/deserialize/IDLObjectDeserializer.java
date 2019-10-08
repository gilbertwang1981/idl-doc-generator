package com.hs.doc.gen.deserialize;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;

import com.google.protobuf.Message.Builder;
import com.googlecode.protobuf.format.JsonFormat;

public class IDLObjectDeserializer {
	public Builder deserialize(String clazz , String objectJsonString) throws Exception {
		int pos = clazz.lastIndexOf(".");
		byte [] clazzTarget = clazz.getBytes();
		clazzTarget[pos] = '$';
		
		Class<?> builderClazz = Class.forName(new String(clazzTarget));
		
		Method method = builderClazz.getMethod("newBuilder");

		Builder builder = (Builder) method.invoke(null);
		new JsonFormat().merge(new ByteArrayInputStream(objectJsonString.getBytes()) ,  builder);
		
		return builder;
	}
}
