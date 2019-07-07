package com.wd.assemble;

import cn.edu.hfut.dmic.webcollector.model.Page;
import com.wd.bean.WdBean;
import com.wd.utils.CodeBeanList;
import com.wd.utils.MyStringUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author xbh
 * @date 2019年7月2日10:00:31
 * @Description 有来相关数据组装
 */

public class YLAssemble {
	public List<WdBean> assemble(List<WdBean> beans) {
		final ArrayList<WdBean> wdBeans = CodeBeanList.wdBean;
		final ArrayList<WdBean> list = new ArrayList<>();

		for (WdBean bean : beans) {
			//  过滤无用词汇
			// 增加或减少长度
			String issue = MyStringUtils.amendWordLengthTo60And90(getBeanIssue(bean));
			// 增加或减少长度
			String result = MyStringUtils.amendWordLengthTo100And130(getBeanResult(bean));
			// 给问题添加标题
			issue += bean.getTitle();

			// 添加标点符号
			result = MyStringUtils.addaHeadCommaChar(result);
			if (MyStringUtils.getSymbolCount(result) < 5) {
				result = MyStringUtils.addaHeadCommaChar(result);
			}

			// 添加标点符号
			issue = MyStringUtils.addaHeadCommaChar(issue);
			if (MyStringUtils.getSymbolCount(issue) < 5) {
				issue = MyStringUtils.addaHeadCommaChar(issue);
			}

			// 删除开始字母
			issue = MyStringUtils.deleteFirstSymbol(issue);
			result = MyStringUtils.deleteFirstSymbol(result);

			// 剔除重复标点
			issue = MyStringUtils.deleteRepeatSymbol1(issue);
			result = MyStringUtils.deleteRepeatSymbol1(result);

			// 添加最终标点
//			issue = MyStringUtils.addLastSymbolI(issue);
			result = MyStringUtils.addLastSymbolI(result);

			bean.setIssue(issue);
			bean.setResult(result);
			final WdBean wdBean = new WdBean();
			wdBean.setAge(bean.getAge());
			wdBean.setSex(bean.getSex());
			wdBean.setResult(bean.getResult());
			wdBean.setIssue(bean.getIssue());

			list.add(wdBean);
			CodeBeanList.wdBean.set(wdBeans.indexOf(bean), null);
		}

		return list;
	}

	private String getBeanResult(WdBean bean) {
		Integer max_words_length = 0;
		Integer max_words_index = 0;
		String max_x = "";

		for (Page page : bean.getPages()) {
			String s = "";
			final Elements b_docYes = page.select("div.docYes");
			if (b_docYes.size() > 0 && b_docYes != null) {
				final Elements p = b_docYes.get(0).getElementsByTag("p");
					s = p.text();
					// 字符数量如果小于80， 或者逗号数量小于5

					s = s.trim();
					s = s.replace(" ", "，");
					s = s.replace("来看", "");
					s = s.replace("或者", "，");

//					if (s.length() < 80 ||( MyStringUtils.containsStromgCount(s, "，") < 5 || MyStringUtils.containsStromgCount(s, ",") < 5) ) {
//						continue;
//					}

					if (s.indexOf("想得到的帮助") != -1) {
						continue;
					}

					if (s.length() < 80 ) {
						continue;
					}

					if (Pattern.matches(".*以上.*问题的建议.*", s) || Pattern.matches(".*很高兴.*", s)) {
						continue;
					}
					// 无用词判断
					for (String string : CodeBeanList.filteStrings) {
						s = s.replace(string, "");
					}


					s = s.replace("?", "。");
					s = s.replace("？", "。");
					s = s.replace("你好", "");
					s = s.replace("您好", "");
					s = s.replace("检查结果", "结果");
					// 字数判断

					if (s.length() > max_words_length) {
						max_words_length = s.length();
						max_x = s;
					}

					if (s.length() >= 120 && s.length() <= 140) {
						return s;
					}
				}
			}
		return max_x;
	}

	private String getBeanIssue(WdBean bean) {
		Integer max_words_length = 0;
		Integer max_words_index = 0;
		ArrayList<String> list = new ArrayList<String>();
		String age = "";
		String sex = "";

		for (Page page : bean.getPages()) {
			String s = "";
			final Elements i = page.select("div.b_askcont");
			if (i.size() > 0){
				final Element issue = i.get(0);
				final Elements p = issue.select("p.crazy_new");
				if (p == null) {
					continue;
				}

				final Elements select = page.select("div.b_askab1");
				final String first = select.get(0).selectFirst("span").text();
				final String[] strings = first.split("|");
				sex = strings[0];
				age = strings[4] + strings[5];
				bean.setAge(age);
				bean.setSex(sex);

				s = p.text();


				s = s.trim();
				s = s.replace("?", "。");
				s = s.replace("？", "。");
				s = s.replace(" ", ",");
				// 增加最后标点符号
				s = MyStringUtils.addLastSymbolI(s);


				// 无用词判断
				for (String string : CodeBeanList.issueString) {
					s = s.replace(string, "");
				}
				// 无用标点符号判断
				for (String string : CodeBeanList.failingChar) {
					s = s.replace(string, ",");
				}

				s = s.replace("?", "。");

				// 如果大于60字直接跳出
				if (s.length() > 60 && s.length() < 80) {
					return s;
				}
				list.add(s);
				// 字数判断
				if (s.length() > max_words_length) {
					max_words_length = s.length();
					max_words_index = list.indexOf(s);
				}
			}
		}

		bean.setAge(age);
		bean.setSex(sex);
		if (list.size() != 0) {
			return list.get(max_words_index);
		}

		return "";
	}
}
