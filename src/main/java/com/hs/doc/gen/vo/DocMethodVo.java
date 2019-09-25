package com.hs.doc.gen.vo;

import java.util.List;

import com.hs.doc.gen.consts.DocRequestMethod;

public class DocMethodVo {
	private String name;
	private List<DocRequestMethod> method;
	private List<String> produces;
	private String desc;
	private String version;
	private String parameterClassName;
	private String returnClassName;
	private String parameterTypeIdl;
	private String returnTypeIdl;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getProduces() {
		return produces;
	}
	public void setProduces(List<String> produces) {
		this.produces = produces;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<DocRequestMethod> getMethod() {
		return method;
	}
	public void setMethod(List<DocRequestMethod> method) {
		this.method = method;
	}
	public String getParameterClassName() {
		return parameterClassName;
	}
	public void setParameterClassName(String parameterClassName) {
		this.parameterClassName = parameterClassName;
	}
	public String getReturnClassName() {
		return returnClassName;
	}
	public void setReturnClassName(String returnClassName) {
		this.returnClassName = returnClassName;
	}
	public String getParameterTypeIdl() {
		return parameterTypeIdl;
	}
	public void setParameterTypeIdl(String parameterTypeIdl) {
		this.parameterTypeIdl = parameterTypeIdl;
	}
	public String getReturnTypeIdl() {
		return returnTypeIdl;
	}
	public void setReturnTypeIdl(String returnTypeIdl) {
		this.returnTypeIdl = returnTypeIdl;
	}
}
