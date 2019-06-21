package com.wd.workspace;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import com.wd.bean.WdBean;
import com.wd.spider.DemoAutoNewsCrawler;
import com.wd.utils.CodeBeanList;

/**
 * @author xbh
 * @date
 * @Description
 */
public class OneWorkSpace {
	public void work(String pathname) throws Exception {
			// 获取第一个url
		final DemoAutoNewsCrawler one = new DemoAutoNewsCrawler(pathname, true);
		for (WdBean bean : CodeBeanList.wdBean) {
			final CrawlDatum datum = one.addSeedAndReturn(bean.getOneUrl().trim());
			datum.meta("id", CodeBeanList.wdBean.indexOf(bean));
		}
		one.start(1);
	}
}
