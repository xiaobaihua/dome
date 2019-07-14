package com.wd.listener;

import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import com.wd.bean.WdBean;
import com.wd.utils.CodeBeanList;
import com.wd.utils.ConfUtils;

import java.util.List;

/**
 * @author xbh
 * @date 2019年6月8日16:30:38
 * @Description 读取
 */
public class YoulaiReaderListener extends AnalysisEventListener{

	@Override
	public void invoke(Object object, AnalysisContext context) {
		if (context.getCurrentRowNum() != 0) {
			List<String> l = (List<String>) object;
			final WdBean wd = new WdBean();
			wd.setTitle(l.get(2));
			wd.setCxeclRowIndex(context.getCurrentRowNum().toString());
			String s = ConfUtils.getProperties("site")+wd.getTitle() + "有来医生";

			for (int i = 0; i < 2; i++) {
				wd.getOneUrl().add(s + "&pn=" + i * 10);
			}
			System.out.println(s);

			CodeBeanList.wdBean.add(wd);
		}
	}

	@Override
	public void doAfterAllAnalysed(AnalysisContext context) {
	}

}
