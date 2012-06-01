package org.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.intro.PhotoJ;

import com.google.common.base.Function;
import com.google.common.collect.Multimaps;

public class CollectionExamplesJ {

	final static List<PhotoJ> dataset = Arrays.asList(new PhotoJ(
			"file:///tmp/sunset.png", 200, Arrays.asList(7, 4, 5, 6, 4, 5, 6)),
			new PhotoJ("file:///tmp/mountain.png", 12302, Arrays.asList(9)),
			new PhotoJ("file:///tmp/duke.png", 50, Arrays.asList(6, 9, 7)));

	public static void doIt_1() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		for (PhotoJ photo : dataset) {
			if (photo.getSizeKb() > 1000) {
				map.put(photo.getURL().toString(), photo.getSizeKb());
			}
		}
	}

	public static void doIt_2() {
		Comparator<Integer> comparator = Collections.reverseOrder();
		List<PhotoJ> sorted = new ArrayList<PhotoJ>();
		for (PhotoJ photo : dataset) {
			List<Integer> ratings = photo.getRatings();
			Collections.sort(ratings, comparator);
			sorted.add(new PhotoJ(photo.getURL().toString(), photo.getSizeKb(),
					ratings));
		}
		Collections.sort(sorted, new Comparator<PhotoJ>() {
			public int compare(PhotoJ photo1, PhotoJ photo2) {
				return photo1.getURL().toString().length()
						- photo2.getURL().toString().length();
			}
		});
	}

	public static void doIt_3() {
		Map<Integer, List<PhotoJ>> map = new HashMap<Integer, List<PhotoJ>>();
		for (PhotoJ photo : dataset) {
			int average = average(photo.getRatings());
			List<PhotoJ> sameAveragePhotos = map.get(average);
			if (sameAveragePhotos == null) {
				ArrayList<PhotoJ> photos = new ArrayList<PhotoJ>();
				photos.add(photo);
				map.put(average, photos);
			} else {
				sameAveragePhotos.add(photo);
			}
		}
	}

	private static Integer average(List<Integer> list) {
		int sum = 0;
		for (Integer value : list) {
			sum += value;
		}
		return sum / list.size(); 
	}

	public static void doIt_3_B() {
		 Map<Integer, Collection<PhotoJ>> map = 
				 Multimaps.index(dataset.iterator(), new Function<PhotoJ, Integer>() {
					public Integer apply(final PhotoJ photo) {
						return average(photo.getRatings());
					}
				}).asMap();
	}

	public static void main(String[] args) {
		doIt_1();
		doIt_2();
		doIt_3();
		doIt_4();
	}

	public static void doIt_4() {
		ArrayList<PhotoJ> smallPhotos = new ArrayList<PhotoJ>();
		ArrayList<PhotoJ> largePhotos = new ArrayList<PhotoJ>();
		for (PhotoJ photo : dataset) {
			if (photo.getSizeKb() > 1000) {
				largePhotos.add(photo);
			} else {
				smallPhotos.add(photo);
			}
		}
	}

}
