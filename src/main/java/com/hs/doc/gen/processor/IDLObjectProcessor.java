package com.hs.doc.gen.processor;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import com.google.gson.Gson;
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
		Object object = target.newInstance();
		
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
				List<Object> data = new ArrayList<>();
				for (Object tobj : (List<?>)entry.getValue()) {						
					@SuppressWarnings("unchecked")
					Object subObject = createAndInitializeObject(elementType , (Map<String , Object>)tobj);
					data.add(subObject);
				}
				
				setter(object , attrName , data , List.class);
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
	
	public void setter(Object obj, String att, Object value, Class<?> type) throws Exception {
		Method met = obj.getClass().getMethod("set" + convertMethodName(att), type);
		met.invoke(obj, value);
	}
	
	public static void main(String [] args) throws Exception {
		Map<String , Object> attrMap = new HashMap<>();
		attrMap.put("companyCode-java.lang.String", "001");
		
		Map<String , Object> subAttrMap = new HashMap<>();
		subAttrMap.put("name-java.lang.String" , "王晗");
		subAttrMap.put("age-java.lang.Integer" , 32);
		subAttrMap.put("sex-int" , 10);
		subAttrMap.put("userId-long" , 100011);
		
		attrMap.put("monitor-com.hs.doc.gen.processor.Employee", subAttrMap);
		
		List<Map<String , Object>> multi = new ArrayList<>();
		for (int i = 0;i < 10; i ++) {
			Map<String , Object> attr = new HashMap<>();
			attr.put("name-java.lang.String" , "王晗" + i);
			attr.put("age-java.lang.Integer" , 32 + i);
			attr.put("sex-int" , 10 + i);
			attr.put("userId-long" , 100011 + i);
			
			multi.add(attr);
		}
		attrMap.put("employee-java.util.List-com.hs.doc.gen.processor.Employee" , multi);
		
		IDLObjectProcessor processor = new IDLObjectProcessor();
		Object object = processor.createAndInitializeObject("com.hs.doc.gen.processor.Company", attrMap);
		System.out.println(new Gson().toJson(object));
	}
}

class Company {
	private String companyCode;
	private List<Employee> employee;
	private Employee monitor;
	
	public String getCompanyCode() {
		return companyCode;
	}
	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}
	public List<Employee> getEmployee() {
		return employee;
	}
	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}
	public Employee getMonitor() {
		return monitor;
	}
	public void setMonitor(Employee monitor) {
		this.monitor = monitor;
	}
}

class Employee {
	private String name;
	private Integer age;
	private int sex;
	private long userId;
	
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
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
}
