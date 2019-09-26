package com.hs.doc.gen.processor;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.hs.doc.gen.consts.DocGeneratorConsts;

public class IDLObjectProcessor {
	
	/**
	 * 
	 * @param clazz 类名字
	 * @param attrTypeValueMap 属性，类型和值映射关系，key：name-java.lang.String value: "this is a test"
	 * 							key: test-com.hs.test.Test value: 是Test类的属性，类型值的一个映射Map
	 * @return 创建和初始化的目标对象
	 * @throws Exception
	 */
	public Object createAndInitializeObject(String clazz , Map<String , Object> attrTypeValueMap) throws Exception {
		Class<?> target = Class.forName(clazz);
		Object object = target.newInstance();
		
		for (Map.Entry<String , Object> entry : attrTypeValueMap.entrySet()) {
			StringTokenizer st = new StringTokenizer(entry.getKey() , "-");
			String attrName = st.nextToken();
			String typeName = st.nextToken();
			
			if (typeName.equals(DocGeneratorConsts.DOC_LABEL_OBJECT_NAME) || 
				typeName.equals(DocGeneratorConsts.DOC_LABEL_STRING_NAME)) {
				setter(object , attrName , entry.getValue() , String.class);
			} else if (typeName.equals(DocGeneratorConsts.DOC_LABEL_DOUBlE_NAME)) {
				setter(object , attrName , entry.getValue() , Double.class);
			} else if (typeName.equals(DocGeneratorConsts.DOC_LABEL_LONG_NAME)) {
				setter(object , attrName , entry.getValue() , Long.class);
			} else if (typeName.equals(DocGeneratorConsts.DOC_LABEL_INT_NAME)) {
				setter(object , attrName , entry.getValue() , Integer.class);
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
		Method met = obj.getClass().getMethod("get" + convertMethodName(att));
		return met.invoke(obj);
	}
	
	public void setter(Object obj, String att, Object value, Class<?>type) throws Exception {
		Method met = obj.getClass().getMethod("set" + convertMethodName(att), type);
		met.invoke(obj, value);
	}
	
	public static void main(String [] args) throws Exception {
		Map<String , Object> attrMap = new HashMap<>();
		attrMap.put("companyCode-java.lang.String", "001");
		
		Map<String , Object> subAttrMap = new HashMap<>();
		subAttrMap.put("name-java.lang.String" , "王晗");
		subAttrMap.put("age-int" , 32);
		
		attrMap.put("employee-com.hs.doc.gen.processor.Employee", subAttrMap);
		
		IDLObjectProcessor processor = new IDLObjectProcessor();
		Company company = (Company)processor.createAndInitializeObject("com.hs.doc.gen.processor.Company", attrMap);
		System.out.println(company.getCompanyCode() + " " + company.getEmployee().getName() + " " + company.getEmployee().getAge());
	}
}

class Company {
	private String companyCode;
	private Employee employee;
	
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}

class Employee {
	private String name;
	private Integer age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
