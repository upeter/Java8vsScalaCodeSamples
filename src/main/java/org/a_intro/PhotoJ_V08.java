package org.a_intro;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TextDocumentJ_V2 implements CopyableJ {
	private final URL url;

	public TextDocumentJ_V2(URL url) {
		this.url = url;
	}

	/**
	 * Utility to the rescue.
	 */
	@Override
	public File copyTo(File target) throws MalformedURLException, IOException {
		return CopyUtil.copyTo(url, target);
		
	}
}
 
/**
 * PhotoJ_V8
 */
public class PhotoJ_V08 implements CopyableJ {

	private final URL url;
	private final int sizeKb;
	private final List<Integer> ratings;

	public PhotoJ_V08(String path, int sizeKb, List<Integer> ratings) {
		this.url = convert(path);
		this.sizeKb = sizeKb;
		this.ratings = ratings;
	}

	public PhotoJ_V08(String path, int sizeKb) {
		this.url = convert(path);
		this.sizeKb = sizeKb;
		ratings = new ArrayList<Integer>();
	}

	/**
	 * Utility to the rescue.
	 */
	@Override
	public File copyTo(File target) throws MalformedURLException, IOException {
		return CopyUtil.copyTo(url, target);
	}

	private static URL convert(String path) {
		try {
			return new URL(path);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public File getFile() {
		try {
			return new File(url.toURI());
		} catch (Exception e) {
			// wrap?
			// return null?
			return null;
		}
	}

	public RatingResult getMaxAndMinRate() {
		return new RatingResult(Collections.max(ratings),
				Collections.max(ratings));
	}

	class RatingResult {
		int maxRate;
		int minRate;

		public RatingResult(int maxRate, int minRate) {
			super();
			this.maxRate = maxRate;
			this.minRate = minRate;
		}

		public int getMaxRate() {
			return maxRate;
		}

		public int getMinRate() {
			return minRate;
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