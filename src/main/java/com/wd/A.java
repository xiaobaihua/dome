package com.wd;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.wd.assemble.XywyAssemble;
import com.wd.assemble.YLAssemble;
import com.wd.bean.WdBean;
import com.wd.utils.CodeBeanList;
import com.wd.utils.ConfUtils;
import com.wd.workspace.ThreeWorkSpace;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xbh
 * @date
 * @Description
 */
public class A {
	public void ks(int index) {
		OutputStream outputStream = null;
		try {
			final String s = FileUtils.readFileToString(new File(String.valueOf(index)+".json"), Charset.defaultCharset());
			List<WdBean> beans = JSON.parseArray(s, WdBean.class);
			CodeBeanList.wdBean = (ArrayList<WdBean>) beans;
			final Integer listSize = Integer.valueOf(ConfUtils.getProperties("ListSize"));

			final List<List<WdBean>> lists = Lists.partition(beans, listSize);
			ThreeWorkSpace workSpace = null;
			final XywyAssemble assemble = new XywyAssemble();
//			final YLAssemble assemble = new YLAssemble();

			outputStream = new FileOutputStream(new File(ConfUtils.getProperties("OutExcelPath")+"\\"+String.valueOf(index+1)+".xlsx"));
			ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX,false);
			final Sheet sheet = new Sheet(1, 0);
			sheet.setSheetName(String.valueOf(index + 1));
			// 保存最终数据
			final ArrayList<List<String>> strings = new ArrayList<>();

			// 3 * 10000
			for (int i = 0; i < lists.size(); i++) {
				final List<WdBean> b = lists.get(i);
				workSpace = new ThreeWorkSpace();
				workSpace.threeUrl(String.valueOf(i), b);

				final List<WdBean> beanList = assemble.assemble(b);
				for (WdBean bean : beanList) {
					final ArrayList<String> list = new ArrayList<>();
					list.add(bean.getIssue());
					list.add(bean.getResult());
					list.add(bean.getAge());
					list.add(bean.getSex());
					strings.add(list);
				}

//				CodeBeanList.wdBean.removeAll(b);

			}
			writer.write0(strings, sheet);
			writer.finish();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void yl(int index) {
		OutputStream outputStream = null;

		try {
			final String s = FileUtils.readFileToString(new File(String.valueOf(index)+".json"), Charset.defaultCharset());
			List<WdBean> beans = JSON.parseArray(s, WdBean.class);
			CodeBeanList.wdBean = (ArrayList<WdBean>) beans;
			final Integer listSize = Integer.valueOf(ConfUtils.getProperties("ListSize"));

			final List<List<WdBean>> lists = Lists.partition(beans, listSize);
			ThreeWorkSpace workSpace = null;
//			final XywyAssemble assemble = new XywyAssemble();
			final YLAssemble assemble = new YLAssemble();

			outputStream = new FileOutputStream(new File(ConfUtils.getProperties("OutExcelPath")+"\\"+String.valueOf(index+1)+"1.xlsx"));
			ExcelWriter writer = new ExcelWriter(outputStream, ExcelTypeEnum.XLSX,false);
			final Sheet sheet = new Sheet(1, 0);
			sheet.setSheetName("123");
			// 保存最终数据
			final ArrayList<List<String>> strings = new ArrayList<>();

			// 3 * 10000
			for (int i = 0; i < lists.size(); i++) {
				final List<WdBean> b = lists.get(i);
				workSpace = new ThreeWorkSpace();
				workSpace.threeUrl(String.valueOf(i), b);
				workSpace = null;

				final List<WdBean> beanList = assemble.assemble(beans);
				for (WdBean bean : beanList) {
					final ArrayList<String> list = new ArrayList<>();
					list.add(bean.getIssue());
					list.add(bean.getResult());
					list.add(bean.getAge());
					list.add(bean.getSex());
					strings.add(list);
				}

//				CodeBeanList.wdBean.removeAll(b);

			}
			writer.write0(strings, sheet);
			writer.finish();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
