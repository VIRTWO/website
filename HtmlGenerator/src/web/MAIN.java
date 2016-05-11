package web;

import java.io.IOException;
import java.text.ParseException;

import web.business.Metadata;
import web.operation.GenerateMetadata;
import web.operation.Setup;
import web.operation.WriteDefaultPostList;
import web.operation.WriteNavigation;
import web.operation.WritePost;
import web.operation.WritePostList;

public class MAIN {

	public static void main(String[] args) throws IOException, ParseException {
		// Given I have very less number of posts so far
		// I can afford to generate everything from scratch all the time

		long startTime = System.nanoTime();

		long operationStartTime = System.nanoTime();
		Setup setup = new Setup();
		setup.call();
		printTimeTaken("Time taken to setup output directories and copy resources (ms): ", operationStartTime);

		operationStartTime = System.nanoTime();
		GenerateMetadata generateMetadata = new GenerateMetadata();
		Metadata metadata = generateMetadata.call();
		printTimeTaken("Time taken to parse input files and generate metadata (ms): ", operationStartTime);

		operationStartTime = System.nanoTime();
		WritePost writePost = new WritePost();
		writePost.call(metadata);
		printTimeTaken("Time taken to generate HTML posts (ms): ", operationStartTime);

		operationStartTime = System.nanoTime();
		WritePostList writePostList = new WritePostList();
		writePostList.call(metadata);
		printTimeTaken("Time taken to generate HTML post lists (ms): ", operationStartTime);

		operationStartTime = System.nanoTime();
		WriteNavigation writeNavigation = new WriteNavigation();
		writeNavigation.call(metadata);
		printTimeTaken("Time taken to generate HTML navigation (ms): ", operationStartTime);

		operationStartTime = System.nanoTime();
		WriteDefaultPostList writeDefaultPostList = new WriteDefaultPostList();
		writeDefaultPostList.call(metadata);
		printTimeTaken("Time taken to generate HTML default post list (ms): ", operationStartTime);

		printTimeTaken("Overall time taken (ms): ", startTime);
	}

	private static void printTimeTaken(String msg, long startTimeNs) {
		long timeTaken = (System.nanoTime() - startTimeNs) / 1000000;
		System.out.println(msg + timeTaken);
	}
}
