package com.hs.doc.gen.vo;

import java.util.List;

public class DocServiceVo {
	private String service;
	private String module;
	private String version;
	private List<DocMethodVo> methods;
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public List<DocMethodVo> getMethods() {
		return methods;
	}
	public void setMethods(List<DocMethodVo> methods) {
		this.methods = methods;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
}
