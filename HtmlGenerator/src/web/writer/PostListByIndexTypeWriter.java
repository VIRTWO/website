package web.writer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import web.business.Metadata;
import web.business.MetadataIndex;
import web.business.Metadatum;
import web.common.FileNameUtility;

public class PostListByIndexTypeWriter extends PostListWriter {

	public PostListByIndexTypeWriter(String outputDirectory, String postListTemplateFile,
			String postListItemTemplateFile) {
		super(outputDirectory, postListTemplateFile, postListItemTemplateFile);
	}

	public void write(String archiveTitle, List<MetadataIndex> metadataIndexes) throws IOException {
		Document templateDocument = Jsoup.parse(new File(postListTemplateFile), "UTF-8");
		templateDocument.getElementById("post-list-title").text(archiveTitle);
		Element contentHolder = templateDocument.getElementById("post-list");
		for (MetadataIndex i : metadataIndexes) {
			String itemHtml = getItemHtml(i.getName(), getLinks(i.getMetadata()), postListItemTemplateFile);
			contentHolder.append(itemHtml);
		}
		write(templateDocument, FileNameUtility.getHtmlFileName(outputDirectory, archiveTitle));
	}

	private Map<String, String> getLinks(Metadata metadata) {
		// links = link:title
		Map<String, String> links = new TreeMap<String, String>();
		for (Metadatum m : metadata) {
			links.put(m.getId(), m.getTitle());
		}
		return links;
	}

	private static String getItemHtml(String title, Map<String, String> links, String postListItemTemplateFile)
			throws IOException {
		String itemTemplateFileHtml = FileUtils.readFileToString(new File(postListItemTemplateFile), Charset.defaultCharset());
		Document itemTemplateDocument = Jsoup.parseBodyFragment(itemTemplateFileHtml);
		// there should be only 1 of these
		Element element = itemTemplateDocument.getElementsByClass("post-list-category-title").get(0);
		element.text(title);
		// there should be only 1 of each of these
		Element list0 = itemTemplateDocument.getElementsByClass("post-list-item-list0").get(0);
		Element list1 = itemTemplateDocument.getElementsByClass("post-list-item-list1").get(0);

		Element[] lists = new Element[] { list0, list1 };
		int listIndex = 0;

		for (Entry<String, String> e : links.entrySet()) {
			listIndex = listIndex % 2;
			addItem(lists[listIndex], e.getValue(), FileNameUtility.addHtmlExtension(e.getKey()));
			listIndex = listIndex + 1;
		}

		return itemTemplateDocument.body().html();
	}

	private static void addItem(Element list, String title, String url) {
		Element item = list.appendElement("li");
		Element link = item.appendElement("a").attr("href", url).attr("class", "w3-text-green");
		link.text(title);
	}

}
