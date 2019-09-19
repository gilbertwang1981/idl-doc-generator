package com.hs.doc.gen.vo;

import java.util.List;

public class DocProjectVo {
	private String project;
	private List<DocServiceVo> services;
	
	public String getProject() {
		return project;
	}
	public void setProject(String project) {
		this.project = project;
	}
	public List<DocServiceVo> getServices() {
		return services;
	}
	public void setServices(List<DocServiceVo> services) {
		this.services = services;
	}
}
