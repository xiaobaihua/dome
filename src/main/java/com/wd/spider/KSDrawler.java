package com.wd.spider;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import com.wd.bean.WdBean;
import com.wd.utils.CodeBeanList;

import java.util.regex.Pattern;

/**
 * @author xbh
 * @date 2019年6月9日11:13:40
 * @Description 爬去并解析快速问医生
 */
public class KSDrawler extends BreadthCrawler {
	static int l = 0;

	/**
	 * 构造一个基于RocksDB的爬虫
	 * RocksDB文件夹为crawlPath，crawlPath中维护了历史URL等信息
	 * 不同任务不要使用相同的crawlPath
	 * 两个使用相同crawlPath的爬虫并行爬取会产生错误
	 *
	 * @param crawlPath RocksDB使用的文件夹
	 * @param autoParse 是否根据设置的正则自动探测新URL
	 */
	public KSDrawler(String crawlPath, boolean autoParse) {
		super(crawlPath, false);
		setResumable(false);
		setThreads(200);
		getConf().setTopN(50000);
		conf.setExecuteInterval(500);



		
		conf.setMaxExecuteCount(3000);
		setMaxExecuteCount(5);
	}

	@Override
	public void visit(Page page, CrawlDatums next) {

		// 如果301 , 302就复制meta数据并且添加到下一个任务
		if (page.code() == 301 || page.code() == 302) {
			WdBean bean = CodeBeanList.wdBean.get(Integer.valueOf(page.meta("id")));
			final String s = page.location();
			if (Pattern.matches(".*120ask.com/question/[0-9].*", s)) {
				if (bean.getThreeUrl().size() < 10) {
					bean.getThreeUrl().add(page.location());
				}
			}
			l++;
			System.out.println(l);
//			for (WdBean bean : CodeBeanList.WdBean) {
//				for (String url : bean.getTowUrl()) {
//					if (url.equals(page.url())) {
//						bean.getThreeUrl().add(page.location());
//					}
//				}
//			}
		}
	}
}
