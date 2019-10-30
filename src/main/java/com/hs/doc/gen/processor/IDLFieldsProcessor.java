package com.hs.doc.gen.processor;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.hs.doc.gen.consts.DocGeneratorConsts;

public class IDLFieldsProcessor {
	public void processFields(Map<String , Object> parameters , String clazz , String upperClazz , String upperField) {		
		try {
			Class<?> targetClass = Class.forName(clazz);
			
			Field [] fields = targetClass.getDeclaredFields();	
			for (Field field : fields) {
				if (field.getName().equals(DocGeneratorConsts.DOC_LABEL_SERIAL_VERSION) || 
					field.getName().equals(DocGeneratorConsts.DOC_LABEL_MEM_INIT) || 
					field.getName().equals(DocGeneratorConsts.DOC_LABEL_DEFAULT_INST) || 
					field.getName().equals(DocGeneratorConsts.DOC_LABEL_PARSER) || 
					field.getName().contains(DocGeneratorConsts.DOC_LABEL_FIELD_NUM) || 
					field.getName().contains(DocGeneratorConsts.DOC_LABEL_BIT_FIELD)) {
					
					continue;
				}
				
				if (clazz.equalsIgnoreCase(upperClazz)) {
					parameters.put(upperField , "recursion");
					return;
				}
				
				if (field.getType().isMemberClass()) {				
					Map<String , Object> subMap = new HashMap<>();
					this.processFields(subMap, field.getType().getName() , clazz , field.getName().substring(0 , field.getName().length() - 1));
					parameters.put(field.getName().substring(0 , field.getName().length() - 1) , subMap);
				} else {
					if (field.getType().getName().equals(DocGeneratorConsts.DOC_LABEL_LIST_NAME)) {
						ParameterizedType pt = (ParameterizedType)field.getGenericType();
						Map<String , Object> subMap = new HashMap<>();
						this.processFields(subMap, pt.getActualTypeArguments()[0].getTypeName() , clazz , field.getName().substring(0 , field.getName().length() - 1));
						parameters.put(field.getName().substring(0 , field.getName().length() - 1) , Arrays.asList(subMap));
					} else {
						parameters.put(field.getName().substring(0 , field.getName().length() - 1) , field.getType().getName());
					}
				}
			}
		} catch (Exception e) {
			System.out.println("异常:" + e.toString());
		}
	}
}
