package web.common;

public class Cleanser {

	public static String removeSpace(String string) {
		return removeSpace(string, "");
	}

	public static String spaceToUnderScore(String string) {
		return removeSpace(string, "_");
	}
	
	public static String spaceToHyphen(String string) {
		return removeSpace(string, "-");
	}
	
	public static String removeSpace(String string, String replacement) {
		return string.replaceAll(" ", replacement);
	}

}
