package com.wd.bean;

import cn.edu.hfut.dmic.webcollector.model.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xbh
 * @date 123
 * @Description 123
 */
public class WdBean {
	private String cxeclRowIndex;
	private ArrayList<String> oneUrl = new ArrayList<String>();
	private List<String> towUrl = new ArrayList<String>();
	private List<String> threeUrl  = new ArrayList<String>();

	private String title;
	private String sex;
	private String age;
	private String issue;
	private String result;
	private List<Page> pages = new ArrayList<Page>();;

	public String getCxeclRowIndex() {
		return cxeclRowIndex;
	}

	public void setCxeclRowIndex(String cxeclRowIndex) {
		this.cxeclRowIndex = cxeclRowIndex;
	}

	public ArrayList<String> getOneUrl() {
		return oneUrl;
	}

	public void setOneUrl(ArrayList<String> oneUrl) {
		this.oneUrl = oneUrl;
	}

	public List<String> getTowUrl() {
		return towUrl;
	}

	public void setTowUrl(List<String> towUrl) {
		this.towUrl = towUrl;
	}

	public List<String> getThreeUrl() {
		return threeUrl;
	}

	public void setThreeUrl(List<String> threeUrl) {
		this.threeUrl = threeUrl;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

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

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}
}
