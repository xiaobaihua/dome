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
 * @date 2019年6月9日08:23:08
 * @Description 寻医问药相关数据组装
 */

public class XywyAssemble {
	public void assemble(List<WdBean> beans) {
		for (WdBean bean : beans) {
			//  过滤无用词汇
			// 增加或减少长度
			String issue = getBeanIssue(bean);
			String result = MyStringUtils.amendWordLengthTo100And130(getBeanResult(bean));
			final int i = issue.indexOf("。");
			if (i == -1 || i + 1 != result.length()) {
				result += "。";
			}

			result = MyStringUtils.addaHeadCommaChar(result);
			if (MyStringUtils.getSymbolCount(result) < 5) {
				result = MyStringUtils.addaHeadCommaChar(result);
			}

			// 判断首行是非为字符
			final char[] chars = result.toCharArray();
			if (chars[0] == '，' ) {
				result = result.replaceFirst("，", "");
			} else if (chars[0] == ',') {
				result = result.replaceFirst(",", "");
			}

			// 摘除遗留无意义字符
			for (String s : CodeBeanList.failingChar) {
				result = result.replace(s, "。");
			}

			bean.setIssue(issue);
			bean.setResult(result);
		}

	}

	private void getBeanSex(WdBean bean) {

	}

	private String getBeanResult(WdBean bean) {
		Integer max_words_length = 0;
		Integer max_words_index = 0;
		String max_x = "";

		for (Page page : bean.getPages()) {
			String s = "";
			final Elements b_answerbox = page.select("div.b_answerbox");
			if (b_answerbox.size() > 0) {
				final Elements b_answerli = b_answerbox.get(0).select("div.crazy_new");
				for (Element element : b_answerli) {

					s = element.getElementsByTag("p").text();
					// 字符数量如果小于80， 或者逗号数量小于5

					s = s.trim();
					s = s.replace(" ", "，");
					s = s.replace("来看", "");
					s = s.replace("或者", "，");

//					if (s.length() < 80 ||( MyStringUtils.containsStromgCount(s, "，") < 5 || MyStringUtils.containsStromgCount(s, ",") < 5) ) {
//						continue;
//					}

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
				final Elements p = issue.select("p");
				if (p == null) {
					continue;
				}
				for (Element element : p) {
					if (element.getElementsByTag("span") != null) {
						element.getElementsByTag("span").remove();
					}
					if (element.getElementsByTag("br") != null) {
						element.getElementsByTag("br").remove();
					}
					s += element.text();
				}
				// 无用词判断
				for (String string : CodeBeanList.filteStrings) {
					s = s.replace(string, "");
				}

				s = s.replace("吗？", "。");
				s = s.replace("呢？", "。");

				s = s + bean.getTitle();
				list.add(s);
				// 字数判断
				if (s.length() > max_words_length) {
					max_words_length = s.length();
					max_words_index = list.indexOf(s);
				}
				final Elements select = page.select("div.b_askab1");
				final String first = select.get(0).selectFirst("span").text();
				final String[] strings = first.split("|");
				sex = strings[0];
				age = strings[4] + strings[5];
				bean.setAge(age);
				bean.setSex(sex);

				if (s.length() >= 40 && s.length() <= 90) {
					list.add(s);
					return s;
				}
			}
			if (list.size() > 0) {
				return list.get(max_words_index);
			}
			return s;
		}

		bean.setAge(age);
		bean.setSex(sex);
		return "";
	}
}
