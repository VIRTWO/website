package web.operation;

import static web.common.Constant.DEFAULT_PARTITION_SIZE;
import static web.common.Constant.HTML_INDEX_PAGE_NAME;
import static web.common.Constant.HTML_INDEX_FILE_NAME;
import static web.common.Constant.HTML_POST_LIST_ITEM_TEMPLATE;
import static web.common.Constant.HTML_POST_LIST_TEMPLATE;
import static web.common.Constant.METADATA_FILE;
import static web.common.Constant.OUT_DIR;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import web.business.Metadata;
import web.business.MetadataIndex;
import web.business.Metadatum;
import web.common.FileNameUtility;
import web.writer.PostListByIndexWriter;

public class WriteDefaultPostList {

	public void call() throws IOException {
		Metadata metadata = Metadata.read(METADATA_FILE);
		call(metadata);
	}

	public void call(Metadata metadata) throws IOException {
		Metadata front = new Metadata();
		for (Metadatum m : metadata) {
			if(!m.isInternal()) {
				front.add(m);
				if (front.size() == DEFAULT_PARTITION_SIZE) {
					break;
				}
			}
		}
		MetadataIndex metadataIndex = new MetadataIndex(HTML_INDEX_PAGE_NAME, front);
		PostListByIndexWriter postListByIndexWriter = new PostListByIndexWriter(OUT_DIR, HTML_POST_LIST_TEMPLATE,
				HTML_POST_LIST_ITEM_TEMPLATE);

		postListByIndexWriter.write(HTML_INDEX_FILE_NAME, metadataIndex);

		String oldFileName = FileNameUtility.getHtmlFileName(OUT_DIR,
				FileNameUtility.getQualifiedFileName(HTML_INDEX_FILE_NAME, HTML_INDEX_PAGE_NAME + "-0"));
		String newFileName = FileNameUtility.getHtmlFileName(OUT_DIR, HTML_INDEX_FILE_NAME.toLowerCase());

		FileUtils.moveFile(new File(oldFileName), new File(newFileName));
	}

}
