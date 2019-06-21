package com.wd.utils;


import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.google.common.collect.Lists;
import com.wd.assemble.XywyAssemble;
import com.wd.bean.WdBean;
import com.wd.listener.ReadListener;
import com.wd.spider.DemoAutoNewsCrawler;
import com.wd.spider.FilterCrawler;
import com.wd.spider.YWBDrawler;

import java.io.*;
import java.util.*;

/**
 * @author xbh
 * @date
 * @Description
 */
public class Test {


	@org.junit.Test
	public void a() {
		System.out.println(MyStringUtils.containsStromgCount("aaaaabbaaa", "b"));
	}

	@org.junit.Test
	public void name() {
		InputStream file = null;
		OutputStream outputStream = null;

		try {
			file = new FileInputStream(new File(ConfUtils.getProperties("excelPath")));
			final ExcelReader reader = new ExcelReader(file, ExcelTypeEnum.XLSX, null
				, new ReadListener());
			reader.read();

			final ArrayList<WdBean> wdBean = CodeBeanList.wdBean;

			List<List<WdBean>> parts = Lists.partition(wdBean, 1000);


//			parts.stream().forEach(list -> {
//				process(list);
//			});
			DemoAutoNewsCrawler one = new DemoAutoNewsCrawler("1aaa", false);
			YWBDrawler tow = new YWBDrawler("2bbb", false);
			FilterCrawler filter = new FilterCrawler("3ccc", false);

			for (List<WdBean> beans : parts) {

				for (WdBean bean : beans) {
					one.addSeed(bean.getOneUrl());
				}
				one.start(10);



				for (WdBean bean : beans) {
					tow.addSeed(bean.getTowUrl());
				}tow.start(30);

				for (WdBean bean : beans) {
					filter.addSeed(bean.getThreeUrl());
				}
				filter.start(10);


			}

//			// 获取第一个url
//			int i = 0;
//			final DemoAutoNewsCrawler one = new DemoAutoNewsCrawler("1", true);
//			for (WdBean bean : CodeBeanList.WdBean) {
//				//CodeBeanList.towMap.put(bean.getCxeclRowIndex(), i);
//				//bean.setTowVal(i);
//				one.addSeed(bean.getOneUrl());
//				//i++;
//			}
//			one.start(10);


			// 获取第二个链接url, (10个, 需过滤)
//			final YWBDrawler tow = new YWBDrawler("2", true);
//			for (WdBean bean : CodeBeanList.WdBean) {
//				tow.addSeed(bean.getTowUrl());
//			}
//			tow.start(20);

//			// 过滤第二个URL
//			final FilterCrawler filter = new FilterCrawler("3", true);
//			for (WdBean bean : CodeBeanList.WdBean) {
//				for (String url : bean.getThreeUrl()) {
//					filter.addSeed(url);
//				}
//			}
//			filter.start(10);


			final XywyAssemble assemble = new XywyAssemble();
			assemble.assemble(CodeBeanList.wdBean);


			//FilterString();

			outputStream = new FileOutputStream(new File(ConfUtils.getProperties("OutExcelPath")));
			final ArrayList<List<String>> strings = new ArrayList<>();
			ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX,false);
			final Sheet sheet = new Sheet(1, 0);
			sheet.setSheetName("123");
			for (WdBean bean : CodeBeanList.wdBean) {
				final ArrayList<String> list = new ArrayList<>();
				list.add(bean.getIssue());
				list.add(bean.getResult());
				list.add(bean.getAge());
				list.add(bean.getSex());

				strings.add(list);
			}

			writer.write0(strings, sheet);
			writer.finish();


		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				file.close();
				outputStream.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
