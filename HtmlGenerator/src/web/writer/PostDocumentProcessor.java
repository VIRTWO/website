package web.writer;

import java.io.IOException;

import org.jsoup.nodes.Document;

interface PostDocumentProcessor {

	public abstract void process(Document document) throws IOException; 
	
}
