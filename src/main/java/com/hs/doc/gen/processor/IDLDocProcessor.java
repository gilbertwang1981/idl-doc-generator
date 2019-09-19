package com.hs.doc.gen.processor;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;

import com.google.auto.service.AutoService;
import com.hs.doc.gen.annotation.DocMethod;
import com.hs.doc.gen.annotation.DocRequest;
import com.hs.doc.gen.annotation.DocResponse;
import com.hs.doc.gen.annotation.DocService;

@AutoService(Processor.class)
public class IDLDocProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {	
		
		for (Element annotation : roundEnv.getElementsAnnotatedWith(DocService.class)) {
			DocService docAnn = annotation.getAnnotation(DocService.class);
			if (docAnn != null) {
				System.out.println("服务：" + docAnn.service());			
				for (Element ann : roundEnv.getElementsAnnotatedWith(DocMethod.class)) {
					DocMethod doc = ann.getAnnotation(DocMethod.class);
					if (doc != null) {
						System.out.println("方法：" + doc.name() + "/" + doc.method()[0] + "/" + doc.produces()[0] + "/" + doc.desc());					
						for (Element anno : roundEnv.getElementsAnnotatedWith(DocRequest.class)) {
							DocRequest docAnno = anno.getAnnotation(DocRequest.class);
							if (docAnno != null) {
								System.out.println("入参：" + docAnno.idl());
							}
						}
						
						for (Element anno : roundEnv.getElementsAnnotatedWith(DocResponse.class)) {
							DocResponse docAnno = anno.getAnnotation(DocResponse.class);
							if (docAnno != null) {
								System.out.println("出参：" + docAnno.idl());
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> annotataions = new LinkedHashSet<String>();
		
		annotataions.add("com.hs.doc.gen.annotation.DocService");
		annotataions.add("com.hs.doc.gen.annotation.DocRequest");
		annotataions.add("com.hs.doc.gen.annotation.DocResponse");
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
