package com.hs.doc.gen.processor;

import java.util.Arrays;
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
import com.hs.doc.gen.annotation.DocService;

@AutoService(Processor.class)
public class IDLDocProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {	
		
		for (Element annotation : roundEnv.getElementsAnnotatedWith(DocService.class)) {
			DocService docAnn = annotation.getAnnotation(DocService.class);
			if (docAnn != null) {
				System.out.println("定义服务：" + docAnn.service());			
				for (Element methodAnno : annotation.getEnclosedElements()) {
					DocMethod doc = methodAnno.getAnnotation(DocMethod.class);
					if (doc != null) {
						System.out.println("\t接口定义：\n" + 
								"\t\t接口名称：" + doc.name() + "\n" + 
								"\t\t请求方法：" + doc.method()[0] + "\n" + 
								"\t\tMIME类型：" + doc.produces()[0] + "\n" + 
								"\t\t接口描述：" + doc.desc() + "\n" + 
								"\t\t接口定义文件：" + Arrays.toString(doc.idl()) + "\n" + 
								"\t\t接口版本：" + doc.version());
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
