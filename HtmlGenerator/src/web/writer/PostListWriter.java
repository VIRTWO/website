package web.writer;

public abstract class PostListWriter extends Writer {

	protected final String outputDirectory;
	protected final String postListTemplateFile;
	protected final String postListItemTemplateFile;

	public PostListWriter(String outputDirectory, String postListTemplateFile, String postListItemTemplateFile) {
		this.outputDirectory = outputDirectory;
		this.postListTemplateFile = postListTemplateFile;
		this.postListItemTemplateFile = postListItemTemplateFile;
	}

}
