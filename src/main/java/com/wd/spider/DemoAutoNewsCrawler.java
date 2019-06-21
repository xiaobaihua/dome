package com.wd.spider;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import com.wd.bean.WdBean;
import com.wd.utils.CodeBeanList;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;

/**
 * Crawling news from github news
 * 自动爬取新闻网站，继承 BreadthCrawler（广度爬虫）
 * BreadthCrawler 是 WebCollector 最常用的爬取器之一
 *
 * @author hu
 */
public class DemoAutoNewsCrawler extends BreadthCrawler {
	public static int i = 0;


	public DemoAutoNewsCrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, false);
		setResumable(false);
		setThreads(500);
		getConf().setTopN(50000);
		conf.setExecuteInterval(500);
		conf.setMaxExecuteCount(3000);
		setMaxExecuteCount(5);
	}


	/**
	 * 必须重写 visit 方法，作用是:
	 * 在整个抓取过程中,只要抓到符合要求的页面,webCollector 就会回调该方法,并传入一个包含了页面所有信息的 page 对象
	 *
	 * @param page
	 * @param next
	 */
	@Override
	public void visit(Page page, CrawlDatums next) {
		final Integer id = Integer.valueOf(page.meta("id"));
		final WdBean bean = CodeBeanList.wdBean.get(id);
		final ArrayList<String> urls = new ArrayList<String>();
		i++;
		Elements s = page.select("div.result");
		for (Element element : s.select("a.c-showurl")) {
			urls.add(element.attr("href"));
		}
		bean.setTowUrl(urls);
		}


//		for (WdBean bean : CodeBeanList.WdBean) {
//			if (bean.getOneUrl().equals(page.url())) {
//				final ArrayList<String> urls = new ArrayList<String>();
//
//				Elements s = page.select("div.result");
//				for (Element element : s.select("a.c-showurl")) {
//					urls.add(element.attr("href"));
//				}
//				bean.setTowUrl(urls);
//			}
//		}
}