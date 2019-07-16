package com.wd.utils;

import java.io.File;

/**
 * @author xbh
 * @date
 * @Description
 */
public class FileUtils {
	public static void deleteFile(File file) {
		if (!file.exists()) {
			return;
		}

		if (file.isFile()) {
			file.delete();
		} else {
			for (File f : file.listFiles()) {
				deleteFile(f);
			}
			if (file.listFiles().length == 0) {
				file.delete();
			}
		}
	}
}
