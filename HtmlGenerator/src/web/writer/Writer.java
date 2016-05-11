package web.writer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Entities.EscapeMode;

public abstract class Writer {

	protected static void applyEscapeMode(Document document) {
		Document.OutputSettings settings = document.outputSettings();
		settings.prettyPrint(true);
		settings.indentAmount(1);
		settings.escapeMode(EscapeMode.base);
		settings.charset("ASCII");
	}

	protected static void write(Document document, String file) throws IOException {
		write(document, file, false);
	}

	protected static void write(Document document, String file, boolean bodyOnly) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file)));
		applyEscapeMode(document);
		if (bodyOnly) {
			bw.write(document.body().html());
		} else {
			bw.write(document.outerHtml());
		}
		bw.close();
	}
}
