package org.a_intro;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * PhotoJ
 */
public class PhotoJ_V05 {

	private final URL url;
	private final int sizeKb;
	private final List<Integer> ratings;

	public PhotoJ_V05(String path, int sizeKb, List<Integer> ratings) {
		this.url = convert(path);
		this.sizeKb = sizeKb;
		this.ratings = ratings;
	}

	public PhotoJ_V05(String path, int sizeKb) {
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

	public RatingResult getMaxAndMinRate() {
		return !ratings.isEmpty() ? new RatingResult(Collections.max(ratings),
				Collections.min(ratings)) : null;
	}

	/**
	 * A whole class for two properties...
	 */
	class RatingResult {
		int maxRate;
		int minRate;

		public RatingResult(int maxRate, int minRate) {
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhotoJ_V05 other = (PhotoJ_V05) obj;
		if (ratings == null) {
			if (other.ratings != null)
				return false;
		} else if (!ratings.equals(other.ratings))
			return false;
		if (sizeKb != other.sizeKb)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ratings == null) ? 0 : ratings.hashCode());
		result = prime * result + sizeKb;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

}
