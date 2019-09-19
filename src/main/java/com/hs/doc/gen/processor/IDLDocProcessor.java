package com.hs.doc.gen.processor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import com.google.auto.service.AutoService;
import com.google.gson.Gson;
import com.hs.doc.gen.annotation.DocMethod;
import com.hs.doc.gen.annotation.DocService;
import com.hs.doc.gen.conf.DocGeneratorConfiguration;
import com.hs.doc.gen.util.DocFile;
import com.hs.doc.gen.vo.DocMethodVo;
import com.hs.doc.gen.vo.DocProjectVo;
import com.hs.doc.gen.vo.DocServiceVo;

@AutoService(Processor.class)
public class IDLDocProcessor extends AbstractProcessor {
	
	private DocGeneratorConfiguration docGeneratorConfiguration = new DocGeneratorConfiguration();

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {	
		DocProjectVo project = new DocProjectVo();
		project.setProject(docGeneratorConfiguration.getProjectName());
		
		List<DocServiceVo> services = new ArrayList<>();
		for (Element annotation : roundEnv.getElementsAnnotatedWith(DocService.class)) {
			DocService docAnn = annotation.getAnnotation(DocService.class);
			if (docAnn != null) {
				DocServiceVo service = new DocServiceVo();
				service.setService(docAnn.service());
				service.setVersion(docAnn.version());
				
				List<DocMethodVo> methods = new ArrayList<>();
				for (Element methodAnno : annotation.getEnclosedElements()) {
					DocMethod doc = methodAnno.getAnnotation(DocMethod.class);
					if (doc != null) {
						DocMethodVo method = new DocMethodVo();
						method.setDesc(doc.desc());
						method.setIdl(Arrays.asList(doc.idl()));
						method.setMethod(Arrays.asList(doc.method()));
						method.setName(doc.name());
						method.setProduces(Arrays.asList(doc.produces()));
						method.setVersion(doc.version());
						
						methods.add(method);
					}
					service.setMethods(methods);
				}			
				services.add(service);	
			}
		}
		
		project.setServices(services);
		
		if (!project.getServices().isEmpty()) {
			try {
				DocFile.write2File(new Gson().toJson(project) , 
					docGeneratorConfiguration.getProjectName() , 
					docGeneratorConfiguration.getDocumentPath());
			} catch (Exception e) {
			}
		}
				
		return false;
	}
	
	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> annotataions = new LinkedHashSet<String>();
		
		annotataions.add("com.hs.doc.gen.annotation.DocService");
		annotataions.add("com.hs.doc.gen.annotation.DocMethod");
		
		return annotataions;
	}
	
	@Override
	public SourceVersion getSupportedSourceVersion() {
		return SourceVersion.latestSupported();
	}
	
	@Override
	public synchronized void init(ProcessingEnvironment processingEnv) {
		super.init(processingEnv);
	}
}
