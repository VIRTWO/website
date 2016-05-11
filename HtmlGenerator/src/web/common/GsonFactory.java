package web.common;

import java.util.Date;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.UtcDateTypeAdapter;

public class GsonFactory {

	public synchronized static Gson createWithUTCdateAdapter() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Date.class, new UtcDateTypeAdapter());
		return gsonBuilder.create();
	}

}
