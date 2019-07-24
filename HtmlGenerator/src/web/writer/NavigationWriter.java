package web.writer;

import static web.common.Constant.HTML_CHRONOLOGY_PAGE_NAME;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import web.common.Cleanser;
import web.common.Constant;
import web.common.FileNameUtility;

public class NavigationWriter extends Writer {

	private final String outputDirectory;
	private final String templateFile;

	public NavigationWriter(String outputDirectory, String templateFile) {
		this.outputDirectory = outputDirectory;
		this.templateFile = templateFile;
	}

	public void write(String name, Map<String, List<String>> categoryByGroup, List<String> chronologies)
			throws IOException {
		String navTemplateHtml = FileUtils.readFileToString(new File(templateFile), Charset.defaultCharset());
		Document document = Jsoup.parseBodyFragment(navTemplateHtml);

		Element element = document.getElementById("chronology-navigation");
		for (String c : chronologies) {
			addLink(element, c, getLink(Constant.CHRONOLOGY_MODE.name(), c));
		}
		addLink(element, "ALL", HTML_CHRONOLOGY_PAGE_NAME + ".html");

		for (Entry<String, List<String>> e : categoryByGroup.entrySet()) {
			String qualifier = Cleanser.removeSpace(e.getKey());
			element = document.getElementById(getNavigationElementId(qualifier));
			for (String c : e.getValue()) {
				addLink(element, c, getLink(qualifier, c));
			}
			addLink(element, "ALL", qualifier + ".html");
		}

		write(document, FileNameUtility.getHtmlFileName(outputDirectory, name), true);
	}

	private static String getNavigationElementId(String qualifier) {
		return qualifier.toLowerCase() + "-navigation";
	}

	private static String getLink(String qualifier, String title) {
		return FileNameUtility.getQualifiedHtmlFileName(qualifier, title + "-0");
	}

	private static void addLink(Element element, String title, String link) {
		element.appendElement("a").attr("href", link).text(title);
	}

}
