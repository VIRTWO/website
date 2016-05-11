package web.operation;

import static web.common.Constant.CHRONOLOGY_MODE;
import static web.common.Constant.HTML_CHRONOLOGY_PAGE_NAME;
import static web.common.Constant.HTML_POST_LIST_ITEM_TEMPLATE;
import static web.common.Constant.HTML_POST_LIST_MULTIPLE_ITEM_TEMPLATE;
import static web.common.Constant.HTML_POST_LIST_TEMPLATE;
import static web.common.Constant.METADATA_FILE;
import static web.common.Constant.OUT_DIR;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import web.business.Metadata;
import web.business.MetadataIndex;
import web.business.MetadataIndexer;
import web.common.Constant;
import web.writer.PostListByIndexTypeWriter;
import web.writer.PostListByIndexWriter;

public class WritePostList {

	public void call() throws IOException {
		Metadata metadata = Metadata.read(METADATA_FILE);
		call(metadata);
	}

	public void call(Metadata metadata) throws IOException {

		Map<String, List<MetadataIndex>> categoryByGroupIndex = MetadataIndexer.indexByGroupAndCategory(metadata);
		List<MetadataIndex> chronologyIndexes = MetadataIndexer.indexByChronology(metadata, CHRONOLOGY_MODE);

		call(categoryByGroupIndex, chronologyIndexes);
	}

	public void call(Map<String, List<MetadataIndex>> categoryByGroupIndex, List<MetadataIndex> chronologyIndexes)
			throws IOException {
		writePerIndexPostList(categoryByGroupIndex, chronologyIndexes);
		writePerIndexTypePostList(categoryByGroupIndex, chronologyIndexes);
	}

	private void writePerIndexPostList(Map<String, List<MetadataIndex>> categoryByGroupIndex,
			List<MetadataIndex> chronologyIndexes) throws IOException {
		PostListByIndexWriter postListByIndexWriter = new PostListByIndexWriter(OUT_DIR, HTML_POST_LIST_TEMPLATE,
				HTML_POST_LIST_ITEM_TEMPLATE);
		for (Entry<String, List<MetadataIndex>> e : categoryByGroupIndex.entrySet()) {
			postListByIndexWriter.write(e.getKey(), e.getValue());
		}
		postListByIndexWriter.write(Constant.CHRONOLOGY_MODE.name(), chronologyIndexes);
	}

	public void writePerIndexTypePostList(Map<String, List<MetadataIndex>> categoryByGroupIndex,
			List<MetadataIndex> chronologyIndexes) throws IOException {

		PostListByIndexTypeWriter postListByIndexTypeWriter = new PostListByIndexTypeWriter(OUT_DIR,
				HTML_POST_LIST_TEMPLATE, HTML_POST_LIST_MULTIPLE_ITEM_TEMPLATE);
		for (Entry<String, List<MetadataIndex>> e : categoryByGroupIndex.entrySet()) {
			postListByIndexTypeWriter.write(e.getKey(), e.getValue());
		}
		postListByIndexTypeWriter.write(HTML_CHRONOLOGY_PAGE_NAME, chronologyIndexes);

		// this is not paginated. We can have may be 10 links per 1KB of HTML
		// so with 100KB we will have 1000 links. We will think about pagination
		// and stuff when we
		// reach that limit
	}
}
