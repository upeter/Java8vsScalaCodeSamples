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

import scala.actors.threadpool.Arrays;

/**
 * PhotoJ
 */
public class PhotoJ implements CopyableJ, Comparable<PhotoJ> {

	final static Logger LOG = LoggerFactory.getLogger(PhotoJ.class);
	private final URL url;
	private final int sizeKb;
	private final List<Integer> ratings;
	private final List<String> formats = Arrays.asList(new String[] { "png",
			"jpg", "jpeg", "gif" });

	public PhotoJ(String path, int sizeKb, List<Integer> ratings) {
		this.url = convert(path);
		this.sizeKb = sizeKb;
		assertIsImage(url);
		this.ratings = ratings;
	}

	public PhotoJ(String path, int sizeKb) {
		this.url = convert(path);
		this.sizeKb = sizeKb;
		assertIsImage(url);
		ratings = new ArrayList<Integer>();
	}


	
	@Override
	public int compareTo(PhotoJ that) {
		return sizeKb - that.getSizeKb();
	}


	/**
	 * Helper method for image assertion
	 * 
	 * @param url
	 */
	private void assertIsImage(URL url) {
		boolean isImage = false;
		for (String format : formats) {
			if (url.getFile().endsWith(format)) {
				isImage = true;
			}
		}
		if (!isImage) {
			throw new IllegalArgumentException(String.format(
					"Url %s is no image", url));
		}
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
			return null;
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

	
}
