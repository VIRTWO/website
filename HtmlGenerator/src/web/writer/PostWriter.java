package web.writer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import web.business.Metadata;
import web.business.Metadatum;
import web.common.FileNameUtility;

public class PostWriter extends Writer {

	private final String inputDirectory;
	private final String outputDirectory;
	private final String templateFile;
	
	private List<PostDocumentProcessor> documentProcessors = new ArrayList<PostDocumentProcessor>();

	public PostWriter(String inputDirectory, String outputDirectory, String templateFile) {
		this.inputDirectory = inputDirectory;
		this.outputDirectory = outputDirectory;
		this.templateFile = templateFile;
		
		documentProcessors.add(new PostExternalTextProcessor(inputDirectory));
	}

	public void write(Metadata metadata) throws IOException {
		for (Metadatum m : metadata) {
			String fileName = m.getId();
			write(fileName, inputDirectory, outputDirectory, templateFile, documentProcessors);
		}
	}

	public void write(Metadatum metadatum) throws IOException {
		String fileName = metadatum.getId();
		write(fileName, inputDirectory, outputDirectory, templateFile, documentProcessors);
	}

	private static void write(String fileName, String inputDirectory, String outputDirectory, String templateFile, List<PostDocumentProcessor> documentProcessors)
			throws IOException {
		String post = FileUtils.readFileToString(new File(inputDirectory + File.separator + fileName), Charset.defaultCharset());
		Document document = Jsoup.parse(new File(templateFile), "UTF-8");
		Element placeHolder = document.getElementById("post");
		placeHolder.html(post);
		
		for(PostDocumentProcessor processor : documentProcessors) {
			processor.process(document);
		}
				
		write(document, FileNameUtility.getHtmlFileName(outputDirectory, fileName));
	}

}
