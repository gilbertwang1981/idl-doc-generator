package com.hs.doc.gen.processor;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import com.hs.doc.gen.consts.DocGeneratorConsts;

public class IDLObjectProcessor {
	
	/**
	 * 
	 * @param clazz 类名字
	 * @param attrTypeValueMap 属性，类型和值映射关系，key：name-java.lang.String value: "this is a test"
	 * 							key: test-com.hs.test.Test value: 是Test类的属性，类型值的一个映射Map
	 * 							如果key是List，格式为属性名-java.util.List-数组中元素类型，比如：
	 * 							employee-java.util.List-com.hs.doc.gen.processor.Employee
	 * @return 创建和初始化的目标对象
	 * @throws Exception
	 */
	public Object createAndInitializeObject(String clazz , Map<String , Object> attrTypeValueMap) throws Exception {
		Class<?> target = Class.forName(clazz);
		Constructor<?> c0=  target.getDeclaredConstructor();   
        c0.setAccessible(true);
        
		Object object = c0.newInstance();
		
		for (Map.Entry<String , Object> entry : attrTypeValueMap.entrySet()) {
			StringTokenizer st = new StringTokenizer(entry.getKey() , "-");
			String attrName = st.nextToken();
			String typeName = st.nextToken();
			
			if (typeName.equals(DocGeneratorConsts.DOC_LABEL_OBJECT_NAME)) {
				setter(object , attrName , entry.getValue() , Object.class);
			} else if (typeName.equals(DocGeneratorConsts.DOC_LABEL_STRING_NAME)) {
				setter(object , attrName , entry.getValue() , String.class);
			} else if (typeName.equals(DocGeneratorConsts.DOC_LABEL_DOUBlE_NAME)) {
				setter(object , attrName , entry.getValue() , double.class);
			} else if (typeName.equals(DocGeneratorConsts.DOC_LABEL_CDOUBLE_NAME)) {
				setter(object , attrName , entry.getValue() , Double.class);
			} else if (typeName.equals(DocGeneratorConsts.DOC_LABEL_LONG_NAME)) {
				setter(object , attrName , entry.getValue() , long.class);
			} else if (typeName.equals(DocGeneratorConsts.DOC_LABEL_CLONG_NAME)) {
				setter(object , attrName , entry.getValue() , Long.class);
			} else if (typeName.equals(DocGeneratorConsts.DOC_LABEL_INT_NAME)) {
				setter(object , attrName , entry.getValue() , int.class);
			} else if (typeName.equals(DocGeneratorConsts.DOC_LABEL_CINT_NAME)) {
				setter(object , attrName , entry.getValue() , Integer.class);
			} else if (typeName.equals(DocGeneratorConsts.DOC_LABEL_LIST_NAME)) {
				String elementType = st.nextToken();
				//List<Object> data = new ArrayList<>();
				for (Object tobj : (List<?>)entry.getValue()) {						
					@SuppressWarnings("unchecked")
					Object subObject = createAndInitializeObject(elementType , (Map<String , Object>)tobj);
					//data.add(subObject);
					
					// 如果采用protobuf协议，使用下面的函数，否则把该行注掉，使用标准的setter方法
					setter4ProtoList(object , attrName , subObject , Class.forName(elementType));
				}
				
				//setter(object , attrName , data , List.class);
			} else {
				@SuppressWarnings("unchecked")
				Object subObject = createAndInitializeObject(typeName , (Map<String , Object>)entry.getValue());
				setter(object , attrName , subObject , Class.forName(typeName));
			}
		}
		
		return object;
	}
	
	private String convertMethodName(String old) {
		return old.substring(0 , 1).toUpperCase() + old.substring(1);
	}
	
	public Object getter(Object obj, String att) throws Exception{
		Method met = obj.getClass().getDeclaredMethod("get" + convertMethodName(att));
		return met.invoke(obj);
	}
	
	public void setter(Object obj, String att, Object value, Class<?> type) throws Exception {
		Method met = obj.getClass().getDeclaredMethod("set" + convertMethodName(att), type);
		met.invoke(obj, value);
	}
	
	public void setter4ProtoList(Object obj, String att, Object value, Class<?> type) throws Exception {
		Method met = obj.getClass().getDeclaredMethod("add" + convertMethodName(att) , type);
		met.invoke(obj, value);
	}
}