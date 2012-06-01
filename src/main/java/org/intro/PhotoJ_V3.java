package org.intro;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * PhotoJ_V3
 */
public class PhotoJ_V3 {

	private final URL url;
	private final int sizeKb;
	private final List<Integer> ratings;

	public PhotoJ_V3(String path, int sizeKb, List<Integer> ratings) {
		this.url = convert(path);
		this.sizeKb = sizeKb;
		this.ratings = ratings;
	}

	public PhotoJ_V3(String path, int sizeKb) {
		this.url = convert(path);
		this.sizeKb = sizeKb;
		ratings = new ArrayList<Integer>();
	}

	private static URL convert(String path) {
		try {
			return new URL(path);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}
	
	public int getSizeKb() {
		return sizeKb;
	}

	public List<Integer> getRatings() {
		return ratings;
	}

	public URL getURL() {
		return url;
	}



}
