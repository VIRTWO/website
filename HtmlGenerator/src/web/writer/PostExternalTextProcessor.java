package web.writer;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class PostExternalTextProcessor implements PostDocumentProcessor {

	private final String inputDirectory;
	
	public PostExternalTextProcessor(String inputDirectory) {
		this.inputDirectory = inputDirectory;
	}
	
	public void process(Document document) throws IOException {
		loadExternalText(document, inputDirectory);
	}
	
	private static void loadExternalText(Document document, String inputDirectory) throws IOException {
		Elements externalSources = document.getElementsByAttribute("data-external-text");
		for (Element e : externalSources) {
			String eFileName = e.attr("data-external-text");
			String externalText = FileUtils.readFileToString(new File(inputDirectory + File.separator + eFileName));
			e.appendText(externalText);
		}
	}
	
}
