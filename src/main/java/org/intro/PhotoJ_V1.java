package org.intro;

import java.util.List;

/**
 * PhotoJ_V1
 */
public class PhotoJ_V1 {

	private final String url;
	private final int sizeKb;
	private final List<Integer> ratings;

	public PhotoJ_V1(String url, int sizeKb, List<Integer> ratings) {
		this.sizeKb = sizeKb;
		this.url = url;
		this.ratings = ratings;
	}

	public int getSizeKb() {
		return sizeKb;
	}

	public List<Integer> getRatings() {
		return ratings;
	}

	public String getURL() {
		return url;
	}

}
