package web.business;

import static web.common.Constant.SNIPPET_LENGTH;

import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import web.common.Constant;

/**
 * Represents the metadata for a post.
 */
public class Metadatum {

	private String id;
	private String title = null;
	private Date date = null;
	private String group;
	private Set<String> categories = null;
	private String snippet = null;
	private String explicitSnippet = null;

	public Metadatum(String id, String title, String group, Date date, String body) {
		this(id, title, group, date, body, SNIPPET_LENGTH);
	}

	public Metadatum(String id, String title, String group, Date date, String body, int snippetlength) {
		this.id = id;
		this.title = title;
		this.setGroup(group);
		this.date = date;
		buildSnippet(body, snippetlength);
		this.categories = new HashSet<String>();
	}

	public String getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public boolean isInternal() {
		return group.equalsIgnoreCase(Constant.INTERNAL_GROUP_NAME);
	}
	
	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public Date getDate() {
		return date;
	}

	public boolean partOfChronology(ChronologyMode mode, String chronologyName) {
		return mode.getDateFormat().format(date).equals(chronologyName);
	}

	public boolean hasCategory(String category) {
		return categories.contains(category);
	}

	public Set<String> getCategories() {
		return categories;
	}

	public void addCategory(String category) {
		this.categories.add(category);
	}

	public void removeCategory(String category) {
		this.categories.remove(category);
	}

	public void setExplicitSnippet(String snippet) {
		this.explicitSnippet = snippet;
	}
	
	public String getSnippet() {
		if(StringUtils.isEmpty(explicitSnippet)) {
			return snippet;
		}
		return explicitSnippet;
	}

	public static Comparator<Metadatum> getDateComparator() {
		return new Comparator<Metadatum>() {
			@Override
			public int compare(Metadatum o1, Metadatum o2) {
				return o2.getDate().compareTo(o1.getDate());
			}
		};
	}

	private void buildSnippet(String body, int snippetlength) {
		if (body.length() > snippetlength) {
			this.snippet = body.substring(0, snippetlength) + " ...";
		} else {
			this.snippet = body;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Metadatum other = (Metadatum) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
