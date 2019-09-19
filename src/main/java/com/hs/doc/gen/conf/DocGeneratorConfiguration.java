package com.hs.doc.gen.conf;

import com.hs.doc.gen.consts.DocGeneratorConsts;

public class DocGeneratorConfiguration {
	private String documentPath;
	private String projectName;
	
	public String getDocumentPath() {
		documentPath = System.getenv("DOC_GEN_PATH");
		if (documentPath == null) {
			return DocGeneratorConsts.DOC_GEN_PATH;
		}
		
		return documentPath;
	}

	public String getProjectName() {
		projectName = System.getenv("DOC_GEN_PROJECT");
		if (projectName == null) {
			return DocGeneratorConsts.DOC_GEN_PROJECT_ROOT_NAME;
		}
		
		return projectName;
	}
}
