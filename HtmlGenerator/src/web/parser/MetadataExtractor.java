package web.parser;

import static web.common.Constant.DEFAULT_DATE_FORMAT;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import web.business.Metadata;
import web.business.Metadatum;

public class MetadataExtractor {

	public static Metadata extract(String inputDirectory) throws IOException, ParseException {
		List<File> files = new ArrayList<File>();
		File[] allFiles = new File(inputDirectory).listFiles();
		for (File f : allFiles) {
			if (f.isFile()) {
				files.add(f);
			}
		}
		return extract(files);
	}

	public static Metadata extract(List<File> files) throws IOException, ParseException {
		Metadata metadata = new Metadata();

		for (File f : files) {
			metadata.add(extract(f));
		}

		return metadata;
	}

	private static Metadatum extract(File file) throws IOException, ParseException {
		String html = FileUtils.readFileToString(file);
		Document document = Jsoup.parseBodyFragment(html);

		String id = file.getName();
		String title = document.getElementById("post-title").text();
		Date date = DEFAULT_DATE_FORMAT.parse(document.getElementById("post-date").text());
		String group = document.getElementById("post-group").text();
		String[] categories = document.getElementById("post-category").text().split(",");
		String body = document.getElementById("post-body").text();
		
		Metadatum m = new Metadatum(id, title, group, date, body);

		Element snippet = document.getElementById("post-snippet");
		if(snippet != null) {
			m.setExplicitSnippet(snippet.html()); 
		}
		
		for (String c : categories) {
			m.addCategory(c.trim());
		}

		return m;
	}
}
