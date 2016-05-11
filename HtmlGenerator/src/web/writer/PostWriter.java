package web.writer;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import web.business.Metadata;
import web.business.Metadatum;
import web.common.FileNameUtility;

public class PostWriter extends Writer {

	private final String inputDirectory;
	private final String outputDirectory;
	private final String templateFile;

	public PostWriter(String inputDirectory, String outputDirectory, String templateFile) {
		this.inputDirectory = inputDirectory;
		this.outputDirectory = outputDirectory;
		this.templateFile = templateFile;
	}

	public void write(Metadata metadata) throws IOException {
		for (Metadatum m : metadata) {
			String fileName = m.getId();
			write(fileName, inputDirectory, outputDirectory, templateFile);
		}
	}

	public void write(Metadatum metadatum) throws IOException {
		String fileName = metadatum.getId();
		write(fileName, inputDirectory, outputDirectory, templateFile);
	}

	private static void write(String fileName, String inputDirectory, String outputDirectory, String templateFile)
			throws IOException {
		String post = FileUtils.readFileToString(new File(inputDirectory + File.separator + fileName));
		Document document = Jsoup.parse(new File(templateFile), "UTF-8");
		Element placeHolder = document.getElementById("post");
		placeHolder.html(post);
		Elements externalSources = document.getElementsByAttribute("data-external-text");
		for (Element e : externalSources) {
			String eFileName = e.attr("data-external-text");
			String externalText = FileUtils.readFileToString(new File(inputDirectory + File.separator + eFileName));
			e.appendText(externalText);
		}
		write(document, FileNameUtility.getHtmlFileName(outputDirectory, fileName));
	}
}
