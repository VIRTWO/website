package web.common;

import java.io.File;
import java.text.SimpleDateFormat;

import web.business.ChronologyMode;

public final class Constant {

	public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

	public static final ChronologyMode CHRONOLOGY_MODE = ChronologyMode.YEARLY;

	public static final int SNIPPET_LENGTH = 400;
	public static final int DEFAULT_PARTITION_SIZE = 5;
	public static final int DEFAULT_TOP_NAVIGATION_SIZE = 4;

	public static final String PROJECT_ROOT = "C:\\Users\\anujpr\\Desktop\\Website";
	public static final String RAW_TEXT_DIR = PROJECT_ROOT + File.separator + "post-raw-text";
	public static final String RAW_HTML_DIR = PROJECT_ROOT + File.separator + "post-raw-html";
	public static final String OUT_DIR = PROJECT_ROOT + File.separator + "post-generated-data";
	public static final String METADATA_FILE = OUT_DIR + File.separator + "post.metadata";
	public static final String RESOURCE_DIR = PROJECT_ROOT + File.separator + "resource";

	public static final String HTML_TOP_NAVIGATION_TEMPLATE = PROJECT_ROOT + File.separator + "template"
			+ File.separator + "top-navigation.html";
	public static final String HTML_POST_TEMPLATE = PROJECT_ROOT + File.separator + "template" + File.separator
			+ "post.html";
	public static final String HTML_POST_LIST_TEMPLATE = PROJECT_ROOT + File.separator + "template" + File.separator
			+ "post-list.html";
	public static final String HTML_POST_LIST_ITEM_TEMPLATE = PROJECT_ROOT + File.separator + "template"
			+ File.separator + "post-list-item.html";
	public static final String HTML_POST_LIST_MULTIPLE_ITEM_TEMPLATE = PROJECT_ROOT + File.separator + "template"
			+ File.separator + "post-list-multiple-item.html";

	public static final String HTML_CHRONOLOGY_PAGE_NAME = "Chronology";
	public static final String HTML_INDEX_PAGE_NAME = "Home";
	public static final String HTML_INDEX_FILE_NAME = "Index";
	public static final String HTML_TOP_NAVIGATION_NAME_PAGE_NAME = "top-navigation";
	
	public static final String INTERNAL_GROUP_NAME = "Internal";
}
