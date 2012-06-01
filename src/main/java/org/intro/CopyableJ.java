package org.intro;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public interface CopyableJ {

	public void copyTo(File target) throws MalformedURLException, IOException;
}
