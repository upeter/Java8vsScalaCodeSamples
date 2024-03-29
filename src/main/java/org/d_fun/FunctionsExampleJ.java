package org.d_fun;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; 

import org.a_intro.PhotoJ;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

public class FunctionsExampleJ {

	private final List<String> formats = Arrays.asList(new String[] {
			"png", "jpg", "jpeg", "gif" });

	// =======================================
	// Imperative
	// =======================================
	
	public void imperativeStyleFilter() {
		File dir = new File("/tmp");
		List<File> photoFiles = new ArrayList<File>();
		for(File file :dir.listFiles()) {
			if(isPhoto(file)) {
				photoFiles.add(file);
			}
		}
		//do something useful with photo files
	}
	

	
	
	
	// =======================================
	// Functional 
	// =======================================
	
	public void functionalStyleFilter() {
		File dir = new File("/tmp");
		File[] photoFiles = dir.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return isPhoto(file);
			}
		});
		//do something useful with photo files
	}
	
	
	
	private boolean isPhoto(File file) {
		for (String ext : formats) {
			if (file.getPath().endsWith(ext)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean isPhotoFunctional(final File file) {
		return CollectionUtils.exists(formats, new Predicate() {
			@Override
			public boolean evaluate(Object extension) {
				return file.getPath().endsWith((String)extension);
			}
		});
	
	}
	
	
	
	

	
	// =======================================
	// Imperative
	// =======================================
	static class PhotoHandlerJImperative {
		private final List<String> formats = Arrays.asList(new String[] {
				"png", "jpg", "jpeg", "gif" });
		/**
		 * Convert to list
		 */
		public List<PhotoJ> convert(File file) {
			List<PhotoJ> photos = new ArrayList<PhotoJ>();
			if (file.isDirectory()) {
				File[] photoFiles = file.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File file, String fileName) {
						return isPhoto(file);
					} 
				});
				if (photoFiles != null) {
					for (File photoFile : photoFiles) {
						photos.add(new PhotoJ(photoFile.getAbsolutePath(), -1));
					} 
				}
			}
			return photos;
		}

		private boolean isPhoto(File file) {
			for (String ext : formats) {
				if (file.getPath().endsWith(ext)) {
					return true;
				}
			}
			return false;
		}

	
		public static void main(String[] args) {
			File file = new File("/Users/urs/Desktop/tmp");
			List<PhotoJ> photos = new PhotoHandlerJImperative()
					.convert(file);
			for (PhotoJ photo : photos) {
				System.out.println(photo);
			}
		}
	}

	
	
	
	
	
	
	
	
	
	// =======================================
	// Functional 
	// =======================================
	interface PhotoCallback {
		public void handle(PhotoJ photo);
	}

	static class PhotoHandlerJFunctional {
		private final List<String> formats = Arrays.asList(new String[] {
				"png", "jpg", "jpeg", "gif" });

		/**
		 * Process list directly
		 */
		public void doWithPhotos(File file, PhotoCallback callback) {
			if (file.isDirectory()) {
				File[] photoFiles = file.listFiles(new FilenameFilter() {
					@Override
					public boolean accept(File file, String fileName) {
						return isPhoto(file);
					}
				});
				if (photoFiles != null) {
					for (File photoFile : photoFiles) {
						callback.handle(new PhotoJ(photoFile.getAbsolutePath(), -1));
					}
				}
			}
		}

		private boolean isPhoto(File file) {
			for (String ext : formats) {
				if (file.getPath().endsWith(ext)) {
					return true;
				}
			}
			return false;
		}


		
		
		public static void main(String[] args) {
			File file = new File("/Users/urs/Desktop/tmp");
			new PhotoHandlerJFunctional().doWithPhotos(file,
					new PhotoCallback() {
						@Override
						public void handle(PhotoJ photo) {
							System.out.println(photo.toString());
						}
					});
		}
	}

	
	
	
	
	
	
	
	
	
	
	// =======================================
	// Imperative Recursive
	// =======================================

	static class PhotoHandlerJImperativeRecursive {
		private final List<String> formats = Arrays.asList(new String[] {
				"png", "jpg", "jpeg", "gif" });

		public List<PhotoJ> convert(File file) {
			return load(file, new ArrayList<PhotoJ>());
		}

		private List<PhotoJ> load(File file, List<PhotoJ> photos) {
			if (isPhoto(file)) {
				photos.add(new PhotoJ(file.getAbsolutePath(), -1));
			} else {
				File[] otherFiles = file.listFiles();
				if (otherFiles != null) {
					for (File otherFile : otherFiles) {
						load(otherFile, photos);
					}
				}
			}
			return photos;
		}

		private boolean isPhoto(File file) {
			for (String ext : formats) {
				if (file.getPath().endsWith(ext)) {
					return true;
				}
			}
			return false;
		}

		public static void main(String[] args) {
			File file = new File("/Users/urs/Desktop/tmp");
			List<PhotoJ> photos = new PhotoHandlerJImperativeRecursive()
					.convert(file);
			for (PhotoJ photo : photos) {
				System.out.println(photo);
			}
		}
	}



	// =======================================
	// Functional Recursive
	// =======================================

	static class PhotoHandlerJFunctionalRecursive {
		private final List<String> formats = Arrays.asList(new String[] {
				"png", "jpg", "jpeg", "gif" });

		public void doWithPhotos(File file, PhotoCallback callback) {
			if (isPhoto(file)) {
				callback.handle(new PhotoJ(file.getAbsolutePath(), -1));
			} else {
				File[] ohterFiles = file.listFiles();
				if (ohterFiles != null) {
					for (File otherFile : ohterFiles) {
						doWithPhotos(otherFile, callback);
					}
				}
			}
		}

		private boolean isPhoto(File file) {
			for (String ext : formats) {
				if (file.getPath().endsWith(ext)) {
					return true;
				}
			}
			return false;
		}
	}

	public static void main(String[] args) {
		File file = new File("/Users/urs/Desktop/tmp");
		new PhotoHandlerJFunctionalRecursive().doWithPhotos(file,
				new PhotoCallback() {
					@Override
					public void handle(PhotoJ photo) {
						System.out.println(photo.toString());
					}
				});
	}

	/**
	 * if (file.isFile()) { callback.handle(new PhotoJ(-1, file.toURI())); }
	 * else { File[] photoFiles = file.listFiles(new FilenameFilter() {
	 * 
	 * @Override public boolean accept(File file, String fileName) { return
	 *           file.isDirectory() || isPhoto(file); } }); if (photoFiles !=
	 *           null) { for (File photo : photoFiles) { doWithPhotos(photo,
	 *           callback); } } }
	 */
}
