package com.whitemagic2014.util;

import com.whitemagic2014.db.DBInitHelper;

public class Path {

	/**
	 * 
	 * @Description:获得当前运行环境path
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 28, 2020 3:22:36 PM
	 */
	public static String getPath() {
		String path = DBInitHelper.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		if (System.getProperty("os.name").contains("dows")) {
			path = path.substring(1, path.length());
		}
		if (path.contains("jar")) {
			path = path.substring(0, path.lastIndexOf("."));
			path = path.substring(0, path.lastIndexOf("/") + 1);
		}
		path = path.replace("target/classes/", "").replace("file:", "");
		return path;

	}

}
