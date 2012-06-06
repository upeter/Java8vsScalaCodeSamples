package org.a_intro;

import static org.apache.commons.io.FileUtils.copyURLToFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * PhotoJ
 */
public class PhotoJ_V09 implements CopyableJ {

	/** logger.. */
	final static Logger LOG = LoggerFactory.getLogger(CopyableJ.class);
	private final URL url;
	private final int sizeKb;
	private final List<Integer> ratings;

	public PhotoJ_V09(String path, int sizeKb, List<Integer> ratings) {
		this.url = convert(path);
		this.sizeKb = sizeKb;
		this.ratings = ratings;
	}

	public PhotoJ_V09(String path, int sizeKb) {
		this.url = convert(path);
		this.sizeKb = sizeKb;
		ratings = new ArrayList<Integer>();
	}

	public File copyTo(File target) throws MalformedURLException, IOException {
		File to = target;
		if (target.isDirectory()) {
			String[] pathElements = url.getFile().split("/");
			to = new File(target, pathElements[pathElements.length - 1]);
			LOG.debug("Copy to dir using filename: " + to);
		}
		if (LOG.isDebugEnabled()) {
			LOG.info("Copy to: " + to + " ...");
		}
		copyURLToFile(url, to);
		if (LOG.isInfoEnabled()) {
			LOG.info("Copied successfully copied to: " + to);
		}
		return to;
	}

	private static URL convert(String path) {
		try {
			return new URL(path);
		} catch (MalformedURLException e) {
			LOG.error("Path is no valid URL: " + path, e);
			throw new IllegalArgumentException(e);
		}
	}


	public RatingResult getMaxAndMinRate() {
		return !ratings.isEmpty() ? new RatingResult(Collections.max(ratings),
				Collections.min(ratings)) : null;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ratings == null) ? 0 : ratings.hashCode());
		result = prime * result + sizeKb;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhotoJ_V09 other = (PhotoJ_V09) obj;
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

}
