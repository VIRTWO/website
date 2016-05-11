package web.operation;

import static web.common.Constant.OUT_DIR;
import static web.common.Constant.RESOURCE_DIR;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Setup {

	/*
	 * This class will clean the output directories, copy all resources and lay
	 * the bed for actual generation.
	 */

	public void call() throws IOException {
		File outputDirectory = new File(OUT_DIR);
		setupOutputDirectory(outputDirectory);
		File resourceDirectory = new File(RESOURCE_DIR);
		copyResources(outputDirectory, resourceDirectory);
	}

	private void copyResources(File outputDirectory, File resourceDirectory) throws IOException {
		File[] files = resourceDirectory.listFiles();
		for (File f : files) {
			if (f.isDirectory()) {
				FileUtils.copyDirectoryToDirectory(f, outputDirectory);
			} else {
				FileUtils.copyFileToDirectory(f, outputDirectory);
			}
		}
	}

	private void setupOutputDirectory(File outputDirectory) throws IOException {
		if (outputDirectory.exists()) {
			File[] files = outputDirectory.listFiles();
			for (File f : files) {
				FileUtils.deleteQuietly(f);
			}
		} else {
			FileUtils.forceMkdir(outputDirectory);
		}
	}
}
