package web.operation;

import static web.common.Constant.METADATA_FILE;
import static web.common.Constant.RAW_HTML_DIR;

import java.io.IOException;
import java.text.ParseException;

import web.business.Metadata;
import web.parser.MetadataExtractor;

public class GenerateMetadata {

	public Metadata call() throws IOException, ParseException {
		Metadata metadata = MetadataExtractor.extract(RAW_HTML_DIR);
		metadata.write(METADATA_FILE);
		return metadata;
	}

}
