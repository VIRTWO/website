package web.business;

import java.text.ParseException;
import java.util.Comparator;
import java.util.Date;

/**
 * Represents a categorized group of metadata.
 */
public class MetadataIndex {

	private final Metadata metadata;
	private final String name;

	public MetadataIndex(String name, Metadata metadata) {
		this.name = name;
		this.metadata = metadata;
	}

	public String getName() {
		return name;
	}

	public Metadata getMetadata() {
		return metadata;
	}

	public static Comparator<MetadataIndex> getLexographicalComparator() {
		return new Comparator<MetadataIndex>() {
			@Override
			public int compare(MetadataIndex o1, MetadataIndex o2) {
				return o1.getName().compareTo(o2.getName());
			}
		};
	}

	public static Comparator<MetadataIndex> getChronologyComparator(ChronologyMode mode) {
		return new Comparator<MetadataIndex>() {
			@Override
			public int compare(MetadataIndex o1, MetadataIndex o2) {
				// here names will be of format of CHRONOLOGY_MODE
				try {
					Date d1 = mode.getDateFormat().parse(o1.getName());
					Date d2 = mode.getDateFormat().parse(o2.getName());
					return d2.compareTo(d1);
				} catch (ParseException e) {
					// fall back to string comparision
					return o1.getName().compareTo(o2.getName());
				}
			}
		};
	}
}
