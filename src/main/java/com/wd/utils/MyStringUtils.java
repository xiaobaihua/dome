package com.wd.utils;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xbh
 * @date
 * @Description
 */
public class MyStringUtils {
	private static List resList = CodeBeanList.reusltTianChongList;
	private static Map<String, String> replace = new HashMap<>();

	static {
		replace.put("和", "并且");
		replace.put("能", "可以");
	}

//	// 是否存在标点
//	public static String

	/**
	*@author xbh
	*@date 2019/6/19 10:20
	*@param s 需要统计字符串
	*@param c 统计字符
	*@return java.lang.Integer 返回数量
	*@description
	*/

	public static Integer containsStromgCount(String s, String c) {
		return s.length() - s.replace(c, "").length();
	}


	public static String amendWordLengthTo100And130(String s) {
		final int length = s.length();

		if (length < 120) {
			return addReutlsStringLengthTo100(s);
		} else if (length > 120) {
			return reduceReutlsStringLengthTo130(s);
		}

		return s;
	}

	public static Integer getSymbolCount(String s) {
		int size = 0;
		for (String symbo : CodeBeanList.symbolList) {
			size += containsStromgCount(s, symbo);
		}

		return size;
	}

	public static String addaHeadCommaChar(String s) {
		int size = 0;
		for (String symbo : CodeBeanList.symbolList) {
			size += containsStromgCount(s, symbo);
		}

		// 如果标点符号少于8个
		// 那么就添加标点符号
		if (size < 12) {
			// 遍历所有可能存在的  符号标识符
			for (String comma : CodeBeanList.aheadCommaString) {
				final int i = s.indexOf(comma);
				String p = null;
				// 如果有
				if (i > 0) {
					final String[] split = s.split(comma);
					for (String s1 : split) {
						if (p == null) {
							p = s1 + "，" +comma;
							// 判断该元素是否为最后一个元素
						}  else if (s1.equals(split[split.length - 1])) {
							p += s1 ;
						} else {
							p += s1 + "，" +comma;
						}
					}
				}
				if (p != null) {
					s = p;
				}
			}
		}

		return s;
	}

	public static String addBackCommaChar(String s) {
		int size = 0;
		for (String symbo : CodeBeanList.symbolList) {
			size += containsStromgCount(s, symbo);
		}

		// 如果标点符号少于8个
		// 那么就添加标点符号
		if (size < 8) {
			// 遍历所有可能存在的  符号标识符
			for (String comma : CodeBeanList.backCommaString) {
				final int i = s.indexOf(comma);
				String p = null;
				// 如果有
				if (i > 0) {
					final String[] split = s.split(comma);
					for (String s1 : split) {
						if (p == null) {
							p = s1 +comma+ "，";
							// 判断该元素是否为最后一个元素
						}  else if (s1.equals(split[split.length - 1])) {
							p += s1 ;
						} else {
							p += s1 + comma+"，";
						}
					}
				}
				if (p != null) {
					s = p;
				}
			}
		}

		return s;
	}

	private static String reduceReutlsStringLengthTo130(String s){
		// 数据分割索引
		final char[] chars = new char[]{',','。','，','；','.',';'};
		// 大于130， 缩小
		if (s.length() > 110) {
			// 存储最大出现次数
			final ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<String, Integer>();

			for (char c : chars) {
				String p = String.copyValueOf(s.toCharArray());
				map.put(String.valueOf(c), s.length() - s.replace(String.valueOf(c), "").length());
			}


			final ArrayList<Map.Entry<String, Integer>> arrayList = new ArrayList<>(map.entrySet());
			Collections.sort(arrayList, new Comparator<Map.Entry<String, Integer>>() {
				@Override
				public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
					return o2.getValue() - o1.getValue();
				}
			});
			final String[] strings = s.split(arrayList.get(0).getKey());


			s = "";
			for (int i = 0; i < strings.length; i++) {
				if (s.length() < 110) {
					s += strings[i];
				}else {
					return s;
				}
			}
		}
		return s;
	}

	private static String addReutlsStringLengthTo100(String s){

		return appendReutlsStringLengthTo100(s);
	}

	private static String appendReutlsStringLengthTo100(String p){
		// 小于100， 填充
		final ArrayList<Integer> list = new ArrayList<>();
		while (p.length() < 130 && !p.equals("") && p != null){
			final Integer i = new Random().nextInt(resList.size());
			if (list.indexOf(i) == -1) {
				list.add(i);
				p = p + resList.get(i);
				if (p.length() > 130) {
					return p;
				}
			}

		}
		return p;
	}
}
