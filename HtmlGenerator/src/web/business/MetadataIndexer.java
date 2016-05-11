package web.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Indexes metadata by category or chronology.
 */
public class MetadataIndexer {

	public static Map<String, List<MetadataIndex>> indexByGroupAndCategory(Metadata metadata) {
		List<MetadataIndex> groupIndex = indexByGroup(metadata);
		Map<String, List<MetadataIndex>> categoryByGroup = new HashMap<String, List<MetadataIndex>>();
		for (MetadataIndex m : groupIndex) {
			categoryByGroup.put(m.getName(), indexByCategory(m.getMetadata()));
		}
		return categoryByGroup;
	}

	public static List<MetadataIndex> indexByGroup(Metadata metadata) {
		// no internal groups by default
		return indexByGroup(metadata, false);
	}
	
	public static List<MetadataIndex> indexByGroup(Metadata metadata, boolean includeInternalGroup) {
		Map<String, Metadata> indexMap = new HashMap<String, Metadata>();
		for (Metadatum m : metadata) {
			if(includeInternalGroup == false && m.isInternal()) {
				continue;
			}
			String group = m.getGroup();
			indexMap.putIfAbsent(group, new Metadata());
			indexMap.get(group).add(m);
		}

		List<MetadataIndex> indexes = new ArrayList<MetadataIndex>();
		for (Entry<String, Metadata> e : indexMap.entrySet()) {
			indexes.add(new MetadataIndex(e.getKey(), e.getValue()));
		}

		Collections.sort(indexes, MetadataIndex.getLexographicalComparator());

		return indexes;
	}

	public static List<MetadataIndex> indexByCategory(Metadata metadata) {
		Map<String, Metadata> indexMap = new HashMap<String, Metadata>();
		for (Metadatum m : metadata) {
			for (String category : m.getCategories()) {
				indexMap.putIfAbsent(category, new Metadata());
				indexMap.get(category).add(m);
			}
		}

		List<MetadataIndex> indexes = new ArrayList<MetadataIndex>();
		for (Entry<String, Metadata> e : indexMap.entrySet()) {
			indexes.add(new MetadataIndex(e.getKey(), e.getValue()));
		}

		Collections.sort(indexes, MetadataIndex.getLexographicalComparator());

		return indexes;
	}

	public static List<MetadataIndex> indexByChronology(Metadata metadata, ChronologyMode mode) {
		return indexByChronology(metadata, mode, false);
	}
	
	public static List<MetadataIndex> indexByChronology(Metadata metadata, ChronologyMode mode, boolean includeInternalGroup) {
		Map<String, Metadata> indexMap = new HashMap<String, Metadata>();
		for (Metadatum m : metadata) {
			if(includeInternalGroup == false && m.isInternal()) {
				continue;
			}
			String key = mode.getDateFormat().format(m.getDate());
			indexMap.putIfAbsent(key, new Metadata());
			indexMap.get(key).add(m);
		}

		List<MetadataIndex> indexes = new ArrayList<MetadataIndex>();
		for (Entry<String, Metadata> e : indexMap.entrySet()) {
			indexes.add(new MetadataIndex(e.getKey(), e.getValue()));
		}

		Collections.sort(indexes, MetadataIndex.getChronologyComparator(mode));

		return indexes;
	}

}
