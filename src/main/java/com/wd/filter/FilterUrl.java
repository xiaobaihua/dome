package com.wd.filter;

import com.wd.spider.FilterCrawler;
import java.util.LinkedList;

/**
 * @author xbh
 * @date 2019年6月9日14:49:49
 * @Description 过滤数据URL
 */
public class FilterUrl {
	public String filter(LinkedList<String> urls) throws Exception {
		final FilterCrawler fc = new FilterCrawler("good", true);
		for (String url : urls) {
			fc.addSeed(url);
		}
		// fc.start(10);

		return "https://www.120ask.com/question/16411347.htm";
	}
}
