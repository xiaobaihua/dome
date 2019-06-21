package com.wd.utils;

import com.wd.xywy;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author xbh
 * @date 2019年6月8日18:08:30
 * @Description 配置文件相关工具类
 */
public class ConfUtils {
	static Properties properties = new Properties();

	static {
		final InputStream resourceAsStream = xywy.class.getClassLoader().getResourceAsStream("conf.properties");
		try {
			properties.load(resourceAsStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getProperties(String key){
		return properties.getProperty(key);
	}
}
