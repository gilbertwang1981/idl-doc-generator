package com.hs.doc.gen.processor;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IDLFieldsProcessor {
	public void processFields(Map<String , Object> parameters , String clazz) {
		try {
			Class<?> targetClass = Class.forName(clazz);
			
			Field [] fields = targetClass.getDeclaredFields();
			for (Field field : fields) {
				if (field.getName().equals("serialVersionUID") || 
					field.getName().equals("memoizedIsInitialized") || 
					field.getName().equals("DEFAULT_INSTANCE") || 
					field.getName().equals("PARSER") || 
					field.getName().contains("FIELD_NUMBE") || 
					field.getName().contains("bitField")) {
					continue;
				}
				
				if (field.getType().isMemberClass()) {
					Map<String , Object> subMap = new HashMap<>();
					this.processFields(subMap, field.getType().getName());
					parameters.put(field.getName().substring(0 , field.getName().length() - 1) , subMap);
				} else {
					if (field.getType().getName().equals("java.util.List")) {
						ParameterizedType pt = (ParameterizedType)field.getGenericType();
						Map<String , Object> subMap = new HashMap<>();
						this.processFields(subMap, pt.getActualTypeArguments()[0].getTypeName());
						parameters.put(field.getName().substring(0 , field.getName().length() - 1) , Arrays.asList(subMap));
					} else {
						parameters.put(field.getName().substring(0 , field.getName().length() - 1) , field.getType().getName());
					}
				}
			}
		} catch (Exception e) {
		}
	}
}
