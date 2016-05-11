package web.writer;

import static web.common.Constant.DEFAULT_DATE_FORMAT;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import web.business.Metadata;
import web.business.MetadataIndex;
import web.business.Metadatum;
import web.common.FileNameUtility;

public class PostListByIndexWriter extends PostListWriter {

	public PostListByIndexWriter(String outputDirectory, String postListTemplateFile, String postListItemTemplateFile) {
		super(outputDirectory, postListTemplateFile, postListItemTemplateFile);
	}

	public void write(String qualifier, List<MetadataIndex> metadataIndexes) throws IOException {
		for (MetadataIndex i : metadataIndexes) {
			write(qualifier, i);
		}
	}

	public void write(String qualifier, MetadataIndex metadataIndex) throws IOException {
		write(qualifier, metadataIndex, outputDirectory, postListTemplateFile, postListItemTemplateFile);
	}

	private static void write(String qualifier, MetadataIndex metadataIndex, String outputDirectory,
			String postListTemplateFile, String postListItemTemplateFile) throws IOException {
		String qualifiedName = FileNameUtility.getQualifiedFileName(qualifier, metadataIndex.getName());
		ListIterator<Metadata> iterator = metadataIndex.getMetadata().partition().listIterator();
		int pageNumber = 0;
		while (iterator.hasNext()) {
			String itemTemplateHtml = FileUtils.readFileToString(new File(postListItemTemplateFile));
			Document postListDocument = Jsoup.parse(new File(postListTemplateFile), "UTF-8");

			Element postListTitle = postListDocument.getElementById("post-list-title");
			postListTitle.text(metadataIndex.getName());

			if (iterator.hasPrevious()) {
				Element postPrevious = postListDocument.getElementById("post-list-previous");
				String previous = FileNameUtility.getPageNumberedFileName(qualifiedName, pageNumber - 1);
				postPrevious.attr("href", FileNameUtility.addHtmlExtension(previous));
			}

			Metadata currentMetadata = iterator.next();

			if (iterator.hasNext()) {
				Element postNext = postListDocument.getElementById("post-list-next");
				String next = FileNameUtility.getPageNumberedFileName(qualifiedName, pageNumber + 1);
				postNext.attr("href", FileNameUtility.addHtmlExtension(next));
			}

			Element postListElement = postListDocument.getElementById("post-list");

			for (Metadatum m : currentMetadata) {
				String itemHtml = getItemHtml(itemTemplateHtml, m);
				postListElement.append(itemHtml);
			}

			String outFileName = FileNameUtility.getPageNumberedFileName(qualifiedName, pageNumber);

			write(postListDocument, FileNameUtility.getHtmlFileName(outputDirectory, outFileName));

			pageNumber = pageNumber + 1;
		}
	}

	private static String getItemHtml(String itemTemplateHtml, Metadatum m) {
		Document document = Jsoup.parseBodyFragment(itemTemplateHtml);
		Elements postLinkElements = document.getElementsByClass("post-link");
		for (Element e : postLinkElements) {
			e.attr("href", m.getId() + ".html");
		}
		Elements postTitleElements = document.getElementsByClass("post-title");
		for (Element e : postTitleElements) {
			e.text(m.getTitle());
		}
		Elements postDateElements = document.getElementsByClass("post-date");
		for (Element e : postDateElements) {
			e.text(DEFAULT_DATE_FORMAT.format(m.getDate()));
		}
		Elements postSnippetElements = document.getElementsByClass("post-snippet");
		for (Element e : postSnippetElements) {
			e.html(m.getSnippet());
		}
		return document.body().html();
	}

}
