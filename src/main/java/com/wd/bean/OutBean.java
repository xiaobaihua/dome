package com.wd.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @author xbh
 * @date
 * @Description
 */
public class OutBean extends BaseRowModel {
	@ExcelProperty(value = "问题" ,index = 0)
	private String issue;

	@ExcelProperty(value = "结果",index = 1)
	private String result;

	public String getIssue() {
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
