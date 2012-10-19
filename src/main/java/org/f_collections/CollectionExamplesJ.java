package org.f_collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.a_intro.PhotoJ;

import com.google.common.base.Function;
import com.google.common.collect.Multimaps;

public class CollectionExamplesJ {

final static List<PhotoJ> dataset = Arrays.asList(
	new PhotoJ("file:///tmp/sunset.png", 200, Arrays.asList(7, 4, 5, 6, 4, 5, 6)),
	new PhotoJ("file:///tmp/mountain.png", 12302, Arrays.asList(9)),
	new PhotoJ("file:///tmp/duke.png", 50, Arrays.asList(6, 9, 7)));



public static void letter_U() {
	Comparator<Integer> comparator = Collections.reverseOrder();
	List<PhotoJ> result = new ArrayList<PhotoJ>();
	for (PhotoJ photo : dataset) {
		List<Integer> ratings = photo.getRatings();
		Collections.sort(ratings, comparator);
		result.add(new PhotoJ(photo.getURL().toString(), 
				photo.getSizeKb(), ratings));
	}
	Collections.sort(result, new Comparator<PhotoJ>() {
		public int compare(PhotoJ photo1, PhotoJ photo2) {
			return photo1.getURL().toString().length()
					- photo2.getURL().toString().length();
		}
	});
}

public static void letter_F() {
	Map<String, Integer> map = new HashMap<String, Integer>();
	for (PhotoJ photo : dataset) {
		if (photo.getSizeKb() > 1000) {
			map.put(photo.getURL().toString(), photo.getSizeKb());
		}
	}
}

public static void letter_N() {
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
		 map.toString();
	}

	public static void main(String[] args) {

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
