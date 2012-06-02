package org.a_intro;

import static org.apache.commons.io.FileUtils.copyURLToFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.a_intro.Photo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.actors.threadpool.Arrays;

/**
 * PhotoJ
 */
public class PhotoJ implements CopyableJ {

	final static Logger LOG = LoggerFactory.getLogger(Photo.class);
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


	public File copyTo(File target) throws MalformedURLException, IOException {
		File to = target;
		if (target.isDirectory()) {
			String[] pathElements = url.getFile().split("/");
			to = new File(target, pathElements[pathElements.length - 1]);
		}
		copyURLToFile(url, to);
		return to;	}

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

	private static URL convert(String path) {
		try {
			return new URL(path);
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public File getFile() {
		try {
			if (LOG.isDebugEnabled()) {
				LOG.debug("Get file for url " + url);
			}
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
