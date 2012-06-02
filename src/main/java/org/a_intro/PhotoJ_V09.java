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
 * PhotoJ_V09
 */
public class PhotoJ_V09 implements CopyableJ {

	/** logger.. */
	final static Logger LOG = LoggerFactory.getLogger(Photo.class);
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
