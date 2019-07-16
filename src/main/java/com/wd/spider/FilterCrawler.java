package com.wd.spider;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.net.Requester;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import com.wd.bean.WdBean;
import com.wd.utils.CodeBeanList;

/**
 * @author xbh
 * @date 2019年6月9日15:30:54
 * @Description 利用爬虫过滤
 */
public class FilterCrawler extends BreadthCrawler {
	static long i = 0;

	public FilterCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, false);
		setThreads(100);
		setResumable(false);
		getConf().setExecuteInterval(1000);
		getConf().setConnectTimeout(5000);
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		if (page.code() == 301 || page.code() == 302) {
			next.addAndReturn(page.location()).meta(page.meta());
			return;
		}

		final WdBean bean = CodeBeanList.wdBean.get(Integer.valueOf(page.meta("id")));
		i++;
		System.out.println(i);
		bean.getPages().add(page);

//		int i = 0;
//		final String url = page.url();
//		// 判断页面是否为最终页面
//		for (WdBean bean : CodeBeanList.wdBean) {
//			for (String s : bean.getThreeUrl()) {
//				// 遍历所有实体的第三连接, 从而定位
//				if (s.equals(url) || s.equals(page.meta("towUrl"))) {
//					bean.getPages().add(page);
//				}
//			}
//		}

	}


//		final Elements dec = page.select("div.b_askcont.p");
//		for (Element element : dec) {
//			System.out.println(element.text());
//		}
}
