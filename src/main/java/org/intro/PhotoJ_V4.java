package org.intro;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * PhotoJ_V4
 */
public class PhotoJ_V4  {

	private final URL url;
	private final int sizeKb;
	private final List<Integer> ratings;

	public PhotoJ_V4(String path, int sizeKb, List<Integer> ratings) {
		this.url = convert(path);
		this.sizeKb = sizeKb;
		this.ratings = ratings;
	}

	public PhotoJ_V4(String path, int sizeKb) {
		this.url = convert(path);
		this.sizeKb = sizeKb;
		ratings = new ArrayList<Integer>();
	}

	private static URL convert(String path) {
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
			return new File(url.toURI());
		} catch (Exception e) {
			// wrap?
			// return null?
			return null;
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
