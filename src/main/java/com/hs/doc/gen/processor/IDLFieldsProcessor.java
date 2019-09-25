package com.hs.doc.gen.processor;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class IDLFieldsProcessor {
	public Map<String , String> processFields(String clazz) {
		Map<String , String> parameters = new HashMap<>();
		try {
			StringBuilder sb = new StringBuilder(clazz);
			sb.replace(clazz.lastIndexOf(".") , clazz.lastIndexOf(".") + 1 , "$");
			Class<?> targetClass = Class.forName(sb.toString());
			
			Field [] fields = targetClass.getDeclaredFields();
			for (Field field : fields) {
				if (field.getName().equals("serialVersionUID") || 
					field.getName().equals("memoizedIsInitialized") || 
					field.getName().equals("DEFAULT_INSTANCE") || 
					field.getName().equals("PARSER") || 
					field.getName().contains("FIELD_NUMBE")) {
					continue;
				}
				
				parameters.put(field.getName().substring(0 , field.getName().length() - 1) , field.getType().getName());
			}
			
			return parameters;
		} catch (Exception e) {
			return Collections.emptyMap();
		}
	}
}
