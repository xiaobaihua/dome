package com.wd.utils;

import com.wd.bean.WdBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author xbh
 * @date 2019年6月9日17:57:49
 * @Description 核心
 */
public class CodeBeanList {
	public static ArrayList<WdBean> wdBean = new ArrayList<WdBean>();

	public static final LinkedList<String> symbolList = new LinkedList<>();

	public static final LinkedList<String> wordStrings = new LinkedList<>();

	public static final LinkedList<String> filteStrings = new LinkedList<>();

	public static final LinkedList<String> reusltTianChongList = new LinkedList<>();

	public static final LinkedList<String> backCommaString = new LinkedList<String>();

	public static final LinkedList<String> aheadCommaString = new LinkedList<String>();

	public static final LinkedList<String> failingChar = new LinkedList<String>();

	// 存放汉子词条， 不带有标点符号
	public static final LinkedList<String> issueString = new LinkedList<String>();

	// 最终可用词条， 带有标点符号
	public static final LinkedList<String> issueFilterString = new LinkedList<String>();


	public static final HashMap towMap = new HashMap();

	public static final String[] user_agents = new String[]{
		"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; AcooBrowser; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
		"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.0; Acoo Browser; SLCC1; .NET CLR 2.0.50727; Media Center PC 5.0; .NET CLR 3.0.04506)",
		"Mozilla/4.0 (compatible; MSIE 7.0; AOL 9.5; AOLBuild 4337.35; Windows NT 5.1; .NET CLR 1.1.4322; .NET CLR 2.0.50727)",
		"Mozilla/5.0 (Windows; U; MSIE 9.0; Windows NT 9.0; en-US)",
		"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Win64; x64; Trident/5.0; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 2.0.50727; Media Center PC 6.0)",
		"Mozilla/5.0 (compatible; MSIE 8.0; Windows NT 6.0; Trident/4.0; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; .NET CLR 1.0.3705; .NET CLR 1.1.4322)",
		"Mozilla/4.0 (compatible; MSIE 7.0b; Windows NT 5.2; .NET CLR 1.1.4322; .NET CLR 2.0.50727; InfoPath.2; .NET CLR 3.0.04506.30)",
		"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN) AppleWebKit/523.15 (KHTML, like Gecko, Safari/419.3) Arora/0.3 (Change: 287 c9dfb30)",
		"Mozilla/5.0 (X11; U; Linux; en-US) AppleWebKit/527+ (KHTML, like Gecko, Safari/419.3) Arora/0.6",
		"Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:1.8.1.2pre) Gecko/20070215 K-Ninja/2.1.1",
		"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9) Gecko/20080705 Firefox/3.0 Kapiko/3.0",
		"Mozilla/5.0 (X11; Linux i686; U;) Gecko/20070322 Kazehakase/0.4.5",
		"Mozilla/5.0 (X11; U; Linux i686; en-US; rv:1.9.0.8) Gecko Fedora/1.9.0.8-1.fc10 Kazehakase/0.5.6",
		"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11",
		"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_3) AppleWebKit/535.20 (KHTML, like Gecko) Chrome/19.0.1036.7 Safari/535.20",
		"Opera/9.80 (Macintosh; Intel Mac OS X 10.6.8; U; fr) Presto/2.9.168 Version/11.52",
		"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.11 TaoBrowser/2.0 Safari/536.11",
		"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.71 Safari/537.1 LBBROWSER",
		"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; LBBROWSER)",
		"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E; LBBROWSER)",
		"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.84 Safari/535.11 LBBROWSER",
		"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
		"Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E; QQBrowser/7.0.3698.400)",
		"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E)",
		"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Trident/4.0; SV1; QQDownload 732; .NET4.0C; .NET4.0E; 360SE)",
		"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; QQDownload 732; .NET4.0C; .NET4.0E)",
		"Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 6.1; WOW64; Trident/5.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C; .NET4.0E)",
		"Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1",
		"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1",
		"Mozilla/5.0 (iPad; U; CPU OS 4_2_1 like Mac OS X; zh-cn) AppleWebKit/533.17.9 (KHTML, like Gecko) Version/5.0.2 Mobile/8C148 Safari/6533.18.5",
		"Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:2.0b13pre) Gecko/20110307 Firefox/4.0b13pre",
		"Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:16.0) Gecko/20100101 Firefox/16.0",
		"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11",
		"Mozilla/5.0 (X11; U; Linux x86_64; zh-CN; rv:1.9.2.10) Gecko/20100922 Ubuntu/10.10 (maverick) Firefox/3.6.10"};


	static {
		symbolList.addLast("，");
		symbolList.addLast("。");
		symbolList.addLast("：");
		symbolList.addLast("？");
		symbolList.addLast("！");
		symbolList.addLast("、");
		symbolList.addLast(",");
		symbolList.addLast(".");
		symbolList.addLast(":");
		symbolList.addLast("?");
		symbolList.addLast("!");
		symbolList.addLast("/");
		symbolList.addLast("·");
		symbolList.addLast("~");
		symbolList.addLast("`");

		wordStrings.addLast("您好");
		wordStrings.addLast("你好");
		wordStrings.addLast("意见建议");
		wordStrings.addLast("指导意见");
		wordStrings.addLast("祝你健康");
		wordStrings.addLast("谢谢");
		wordStrings.addLast("我来给你解释一下");
		wordStrings.addLast("性别");
		wordStrings.addLast("年龄");
		wordStrings.addLast("想问一下");
		wordStrings.addLast("病情分析");
		wordStrings.addLast("祝你早日康复");
		wordStrings.addLast("祝您早日康复");
		wordStrings.addLast("祝你生活愉快");
		wordStrings.addLast("祝您生活愉快");
		wordStrings.addLast("病情分析");
		wordStrings.addLast("问题分析");
		wordStrings.addLast("医生询问");
		wordStrings.addLast("回答");
		wordStrings.addLast("希望");
		wordStrings.addLast("能够帮助你");
		wordStrings.addLast("能够帮助到你");
		wordStrings.addLast("医生询问");

		reusltTianChongList.add("注意保持心情舒畅。");
		reusltTianChongList.add("饮食均衡营养。");
		reusltTianChongList.add("避免偏食。");
		reusltTianChongList.add("注意休息。");
		reusltTianChongList.add("避免劳累。");
		reusltTianChongList.add("多吃蔬菜水果。");
		reusltTianChongList.add("避免生冷辛辣食物的摄入。");
		reusltTianChongList.add("规律生活。");
		reusltTianChongList.add("建立合理的饮食习惯和结构。");
		reusltTianChongList.add("多饮温开水。");
		reusltTianChongList.add("饮食注意清淡。");
		reusltTianChongList.add("好好调养。");



		backCommaString.add("检查");
		backCommaString.add("常见");
		backCommaString.add("等");
		backCommaString.add("医院");
		backCommaString.add("地方");
		backCommaString.add("生活环境");
		backCommaString.add("卫生");
		backCommaString.add("晾晒");
		backCommaString.add("办法");
		backCommaString.add("活动");
		backCommaString.add("食物");
		backCommaString.add("区别");
		backCommaString.add("匮乏");
		backCommaString.add("出现");
		backCommaString.add("的话");
		backCommaString.add("不好的");


		aheadCommaString.add("如果");
		aheadCommaString.add("因为");
		aheadCommaString.add("第一次");
		aheadCommaString.add("经过");
		aheadCommaString.add("现在");
		aheadCommaString.add("再次");
		aheadCommaString.add("建议");
		aheadCommaString.add("而且");
		aheadCommaString.add("太大关系");
		aheadCommaString.add("否则");
		aheadCommaString.add("服用");
		aheadCommaString.add("不仅");
		aheadCommaString.add("通常");
		aheadCommaString.add("但");
		aheadCommaString.add("所以");
		aheadCommaString.add("并在");
		aheadCommaString.add("避免");
		aheadCommaString.add("更加");
		aheadCommaString.add("具有");
		aheadCommaString.add("能够");
		aheadCommaString.add("以防");
		aheadCommaString.add("那就");
		aheadCommaString.add("但是");
		aheadCommaString.add("由个人");
		aheadCommaString.add("可以使用");
		aheadCommaString.add("是由于");
		aheadCommaString.add("虽然");
		aheadCommaString.add("也就是");

		issueString.add("健康咨询描述");
		issueString.add("吗");
		issueString.add("啊");
		issueString.add("呀");
		issueString.add("");
		issueString.add("不知道这是怎么回事");
		issueString.add("问题描述");
		issueString.add("您好");
		issueString.add("你好");
		issueString.add("医生好");
		issueString.add("医生你好");
		issueString.add("医生您好");
		issueString.add("医生");
		issueString.add("曾经的治疗情况和效果");
		issueString.add("谢谢");
		issueString.add("请问");
		issueString.add("可以做");
		issueString.add("请");
		issueString.add("想得到的帮助");
		issueString.add("主要症状");
		issueString.add("患者性别");
		issueString.add("患者年龄");
		issueString.add("相关的");


		for (String  word : wordStrings) {
			for (String symbo : symbolList ) {
				filteStrings.addLast(word + symbo);
			}
		}

		// 添加无意义字符
		for (String s : symbolList) {
			for (String s1 : issueString) {
				final String a = s + s1;
				final String b = s1 + s;
				issueFilterString.add(a);
				issueFilterString.add(b);
			}
		}

		// 组合过滤字符集
		for (String s : symbolList) {
			for (String s1 : symbolList) {
				// 犹豫是两个一队， 会出现重复
				final String a = s + s1;
				final String b = s1 + s;
				// 不重复两个都添加进去
				if (!a.equals(b)) {
					failingChar.add(b);
					failingChar.add(a);
				} else {
					// 重复就添加一个
					failingChar.add(b);
				}

			}
		}
	}

}
