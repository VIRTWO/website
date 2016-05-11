package web.common;

import java.io.File;

public class FileNameUtility {

	public static String getQualifiedFileName(String qualifier, String name) {
		return qualifier + "-" + Cleanser.spaceToUnderScore(name);
	}

	public static String getQualifiedHtmlFileName(String qualifier, String name) {
		return addHtmlExtension(getQualifiedFileName(qualifier, name));
	}

	public static String getHtmlFileName(String directory, String fileName) {
		return addHtmlExtension(directory + File.separator + fileName);
	}

	public static String addHtmlExtension(String string) {
		return string + ".html";
	}
	
	public static String getPageNumberedFileName(String name, int pageNumber) {
		return FileNameUtility.getQualifiedFileName(name, String.valueOf(pageNumber));
	}
}
