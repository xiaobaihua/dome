package com.wd.workspace;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import com.wd.bean.WdBean;
import com.wd.spider.YWBDrawler;
import com.wd.utils.CodeBeanList;

/**
 * @author xbh
 * @date
 * @Description
 */
public class TwoWorkSpace {
	public void work(String pathname) throws Exception {
		// 获取第二个链接url, (10个, 需过滤)
		final YWBDrawler tow = new YWBDrawler(pathname, true);
		for (WdBean bean : CodeBeanList.wdBean) {
			for (CrawlDatum datum : tow.addSeedAndReturn(bean.getTowUrl())) {
				datum.url(datum.url().trim());
				datum.meta("id", CodeBeanList.wdBean.indexOf(bean));
			}
		}
		tow.start(4);
	}
}
