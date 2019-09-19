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
import com.hs.doc.gen.annotation.IDLDoc;

@AutoService(Processor.class)
public class IDLDocProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {	
		
		for (Element annotation : roundEnv.getElementsAnnotatedWith(IDLDoc.class)) {
			IDLDoc docAnn = annotation.getAnnotation(IDLDoc.class);
			if (docAnn != null) {
				System.out.println("处理到注解：" + docAnn.description() + "/" + docAnn.version());
			}
		}
		
		return false;
	}
	
	@Override
	public Set<String> getSupportedAnnotationTypes() {
		Set<String> annotataions = new LinkedHashSet<String>();
		
		annotataions.add("com.hs.doc.gen.annotation.IDLDoc");
		
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
