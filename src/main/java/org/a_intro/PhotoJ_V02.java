package org.a_intro;

import java.util.ArrayList;
import java.util.List;

/**
 * PhotoJ_V2
 */
public class PhotoJ_V02 {

	private final String url;
	private final int sizeKb;
	private final List<Integer> ratings;

	public PhotoJ_V02(String url, int sizeKb, List<Integer> ratings) {
		this.sizeKb = sizeKb;
		this.url = url;
		this.ratings = ratings;
	}

	public PhotoJ_V02(String url, int sizeKb) {
		this.sizeKb = sizeKb;
		this.url = url;
		ratings = new ArrayList<Integer>();
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
