package com.wd;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.wd.bean.WdBean;
import com.wd.listener.ReadListener;
import com.wd.listener.YoulaiReaderListener;
import com.wd.utils.CodeBeanList;
import com.wd.utils.ConfUtils;
import com.wd.workspace.OneWorkSpace;
import com.wd.workspace.TwoWorkSpace;
import com.wd.workspace.YLTwoWorkSpace;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;

/**
 * @author xbh
 * @date 2019年6月8日10:41:15
 * @Description 寻医问药
 */
public class xywy {
	public static void main(String[] args){
		yl();
	}

	public static void yl() {
		InputStream file = null;
		OutputStream outputStream = null;

		try {
			file = new FileInputStream(new File(ConfUtils.getProperties("excelPath")));
			final ExcelReader reader = new ExcelReader(file, ExcelTypeEnum.XLSX, null
				, new YoulaiReaderListener());
			reader.read();

			// 获取最终结果页连接
			final OneWorkSpace oneWorkSpace = new OneWorkSpace();
			final YLTwoWorkSpace twoWorkSpace = new YLTwoWorkSpace();

			oneWorkSpace.work("1");

			System.out.println("第一次采集已完结");


			twoWorkSpace.work("2");
			System.out.println("第二次采集已完结");


			final List<List<WdBean>> partition = Lists.partition(CodeBeanList.wdBean, 5000);
			for (int i = 0; i < partition.size(); i++) {
				final List<WdBean> list = partition.get(i);
				final String json = JSON.toJSONString(list);
				FileUtils.write(new File(String.valueOf(i) + ".json"), json, Charset.defaultCharset());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (file != null) {
					file.close();
				}

				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public static void ks() {
		InputStream file = null;
		OutputStream outputStream = null;

		try {
			file = new FileInputStream(new File(ConfUtils.getProperties("excelPath")));
			final ExcelReader reader = new ExcelReader(file, ExcelTypeEnum.XLSX, null
				, new ReadListener());
			reader.read();

			// 获取最终结果页连接
			final OneWorkSpace oneWorkSpace = new OneWorkSpace();
			final TwoWorkSpace twoWorkSpace = new TwoWorkSpace();

			oneWorkSpace.work("1");

			System.out.println("第一次采集已完结");


			twoWorkSpace.work("2");
			System.out.println("第二次采集已完结");


			final List<List<WdBean>> partition = Lists.partition(CodeBeanList.wdBean, 5000);
			for (int i = 0; i < partition.size(); i++) {
				final List<WdBean> list = partition.get(i);
				final String json = JSON.toJSONString(list);
				FileUtils.write(new File(String.valueOf(i) + ".json"), json, Charset.defaultCharset());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (file != null) {
					file.close();
				}

				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}


	}

}
