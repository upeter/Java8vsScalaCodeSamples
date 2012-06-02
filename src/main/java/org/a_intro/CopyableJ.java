package org.a_intro;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public interface CopyableJ {

	public File copyTo(File target) throws MalformedURLException, IOException;
}
