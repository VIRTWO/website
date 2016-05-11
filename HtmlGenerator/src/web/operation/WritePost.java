package web.operation;

import static web.common.Constant.HTML_POST_TEMPLATE;
import static web.common.Constant.METADATA_FILE;
import static web.common.Constant.OUT_DIR;
import static web.common.Constant.RAW_HTML_DIR;

import java.io.IOException;

import web.business.Metadata;
import web.writer.PostWriter;

public class WritePost {

	public void call() throws IOException {
		call(Metadata.read(METADATA_FILE));
	}

	public void call(Metadata metadata) throws IOException {
		PostWriter postWriter = new PostWriter(RAW_HTML_DIR, OUT_DIR, HTML_POST_TEMPLATE);
		postWriter.write(metadata);
	}
}
