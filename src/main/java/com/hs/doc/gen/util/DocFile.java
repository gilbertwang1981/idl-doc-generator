package com.hs.doc.gen.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class DocFile {
	public static void write2File(String content , String projectName , String projectPath) throws IOException {
		File file = new File(projectPath + "/" + projectName);
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(file);
			fileOutputStream.write(content.getBytes());
		} catch (Exception e) {
			throw e;
		} finally {
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
		}
	}
}
