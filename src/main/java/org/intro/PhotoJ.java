package org.intro;

import static org.apache.commons.io.FileUtils.copyURLToFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import scala.actors.threadpool.Arrays;

/**
 * Start with name for Photo URI, refactor
 */
public class PhotoJ implements CopyableJ {

	final static Logger LOG = LoggerFactory.getLogger(Photo.class);
	private final int sizeKb;
	private final URL url;
	private final List<Integer> ratings;
	private final List<String> formats = Arrays.asList(new String[] { "png",
			"jpg", "jpeg", "gif" });

	public PhotoJ(String path, int sizeKb, List<Integer> ratings) {
		super();
		this.sizeKb = sizeKb;
		this.url = convert(path);
		assertIsImage(url);
		this.ratings = ratings;
	}

	public PhotoJ(String path, int sizeKb) {
		super();
		this.sizeKb = sizeKb;
		this.url = convert(path);
		assertIsImage(url);
		ratings = new ArrayList<Integer>();
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

	public void copyTo(File target) throws MalformedURLException, IOException {
		copyURLToFile(url, target);
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

	/**
	 * conversion method
	 * 
	 * @param url
	 */
	private URL convert(String path) {
		try {
			return new URI(path).toURL();
		} catch (MalformedURLException e) {
			throw new IllegalArgumentException(e);
		} catch (URISyntaxException e) {
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
			;
		}

		public int getMaxRate() {
			return maxRate;
		}

		public int getMinRate() {
			return minRate;
		}

	}

}
