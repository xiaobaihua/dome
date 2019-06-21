package com.wd.workspace;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import com.wd.bean.WdBean;
import com.wd.spider.FilterCrawler;
import com.wd.utils.CodeBeanList;

import java.util.List;

/**
 * @author xbh
 * @date 2019年6月13日21:24:34
 * @Description 最终数据
 */
public class ThreeWorkSpace {
	public void threeUrl(String pathname, List<WdBean> beans) throws Exception {

		// 过滤第二个URL
		final FilterCrawler filter = new FilterCrawler(pathname, true);
		for (WdBean bean : beans) {
			final CrawlDatums datums = filter.addSeedAndReturn(bean.getThreeUrl());
			for (CrawlDatum datum : datums) {
				datum.meta("id", CodeBeanList.wdBean.indexOf(bean));
			}
		}

		filter.start(5);
	}
}
