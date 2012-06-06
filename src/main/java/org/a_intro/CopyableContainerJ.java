package org.a_intro;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public class CopyableContainerJ {

	List<CopyableJ> copyables;

	public CopyableContainerJ(List<CopyableJ> copyables) {
		this.copyables = copyables;
	}

	/**
	 * Class method
	 */
	public void copyToDir(File dir) throws MalformedURLException, IOException {
		if(dir.isDirectory()) {
			for(CopyableJ copyable:copyables) {
				copyable.copyTo(dir);
			}
		}
	}
	
}

class CopyableJUtil {
	/**
	 * Utility method
	 */
	public static void copyToDir(File dir, List<CopyableJ> copyables) throws MalformedURLException, IOException {
		if(dir.isDirectory()) {
			for(CopyableJ copyable:copyables) {
				copyable.copyTo(dir);
			}
		}
	}
	
}
