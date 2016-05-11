package web.operation;

import static web.common.Constant.CHRONOLOGY_MODE;
import static web.common.Constant.DEFAULT_TOP_NAVIGATION_SIZE;
import static web.common.Constant.HTML_TOP_NAVIGATION_NAME_PAGE_NAME;
import static web.common.Constant.HTML_TOP_NAVIGATION_TEMPLATE;
import static web.common.Constant.OUT_DIR;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import web.business.Metadata;
import web.business.MetadataIndex;
import web.business.MetadataIndexer;
import web.writer.NavigationWriter;

public class WriteNavigation {

	public void call(Metadata metadata) throws IOException {
		Map<String, List<MetadataIndex>> categoryByGroupIndex = MetadataIndexer.indexByGroupAndCategory(metadata);
		List<MetadataIndex> chronologyIndexes = MetadataIndexer.indexByChronology(metadata, CHRONOLOGY_MODE);
		call(categoryByGroupIndex, chronologyIndexes);
	}

	public void call(Map<String, List<MetadataIndex>> categoryByGroupIndex, List<MetadataIndex> chronologyIndexes)
			throws IOException {
		// we do not want any internal pages in navigation
		NavigationWriter navigationWriter = new NavigationWriter(OUT_DIR, HTML_TOP_NAVIGATION_TEMPLATE);

		Map<String, List<String>> categoryByGroup = new HashMap<String, List<String>>();
		for (Entry<String, List<MetadataIndex>> e : categoryByGroupIndex.entrySet()) {
			categoryByGroup.put(e.getKey(), getNavigationData(e.getValue()));
		}
		List<String> chronologies = getNavigationData(chronologyIndexes);

		navigationWriter.write(HTML_TOP_NAVIGATION_NAME_PAGE_NAME, categoryByGroup, chronologies);

	}

	private static List<String> getNavigationData(List<MetadataIndex> metadataIndexes) {
		int toExtract = DEFAULT_TOP_NAVIGATION_SIZE > metadataIndexes.size() ? metadataIndexes.size()
				: DEFAULT_TOP_NAVIGATION_SIZE;
		List<String> navigationData = new ArrayList<String>();
		for (int i = 0; i < toExtract; i++) {
			navigationData.add(metadataIndexes.get(i).getName());
		}
		return navigationData;
	}

}
