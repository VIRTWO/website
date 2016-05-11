package web.business;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Defines the chronology indexing mode for posts. For example posts can be
 * grouped by year, or month or so on.
 */
public enum ChronologyMode {

	YEARLY(new SimpleDateFormat("yyyy")), MONTHLY(new SimpleDateFormat("MMMM-yyyy"));

	private final DateFormat dateFormat;

	private ChronologyMode(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}
}
