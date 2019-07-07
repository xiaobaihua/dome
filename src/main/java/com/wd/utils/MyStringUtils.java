package com.wd.utils;

import cn.edu.hfut.dmic.webcollector.model.Page;

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

	public static String amendWordLengthTo60And90(String s) {
		final int length = s.length();
		if (length < 60) {
			return addIssueStringLengthTo60(s);
		} else if (length > 80) {
			return reduceIssueStringLengthTo90(s);
		}

		return s;
	}

	private static String reduceIssueStringLengthTo90(String s) {
		// 数据分割索引
		final char[] chars = new char[]{',','。','，','；','.',';'};

		// 大于80， 缩小
		if (s.length() > 80) {
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
				if (s.length() < 40) {
					s += strings[i] + "，";
				}else {
					return s;
				}
			}
		}
		return s + "。";
	}

	private static String addIssueStringLengthTo60(String s) {
		return s;
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

	public static String deleteFirstSymbol(String s) {
		// 判断首行是否为字符
		final char[] target = s.toCharArray();
		for (String symbol : CodeBeanList.symbolList) {
			if (symbol == null || symbol.length() == 0) {
				continue;
			}
			if (target == null || symbol.length() == 0) {
				continue;
			}


			char c = symbol.toCharArray()[0];
			// 下标为0， 1， 2都有可能是字符， 都剔除
			if (target.length > 1 && target[0] == c ) {
				s = s.replaceFirst(String.valueOf(c), "");
			} else if (target.length > 2 && target[1] == c) {
				s = s.replaceFirst(String.valueOf(c), "");
			} else if (target.length > 3 &&target[2] == c) {
				s = s.replaceFirst(String.valueOf(c), "");
			}
		}


		return s;
	}

	// 判断最后一个符号是否为“。”
	public static String addLastSymbolI(String target) {
		// 获取最后一个字符
		final char[] chars = target.toCharArray();
		if (chars.length > 0 && chars != null) {
			char c = chars[target.length() - 1];
			// 判断最后一个字符
			if (!"。".equals(c)) {
				// 判断是否为标点符号
				for (String s : CodeBeanList.symbolList) {
					// 是标点符号， 但不是‘。’
					if (s.equals(String.valueOf(c))) {
						chars[target.length() - 1] = '。';
						return new String(chars);
					}
				}
				return target + '。';
			}
		}

		return target;


//		// 获取最后一个“。”, 判断是否是最后一个字符
//		final int lastIndexOf = target.lastIndexOf("。");
//		if (lastIndexOf != target.length() -1) {
//			target = target + "。";
//		}
//
//		return target;
	}


	public static String deleteRepeatSymbol1(String target) {
		// 默认循环开关
		boolean flag = true;
		// 不存在就跳出， 存在就一直剔除
		while (flag) {
			// 判断存不存在
			for (String s : CodeBeanList.failingChar) {
				flag = (-1 != target.indexOf(s));
				// 一旦发现有无用字符， 就直接跳出， 进行剔除
				if (flag) {
					break;
				}
			}


			// 剔除
			for (String s : CodeBeanList.failingChar) {
				target = target.replace(s, ",");
			}


		}
		return target;
	}

	/**
	*@author xbh
	*@date 2019/6/25 21:36
	*@param target
	*@return java.lang.String
	*@description 
	*/
	public static String deleteRepeatSymbol(String target) {
		final char[] chars = target.toCharArray();
		for (char c : chars) {
			// 当前下标
			Long cIndex = Long.valueOf(target.indexOf(String.valueOf(c)));
			// 下一位下标
			Long nIndex = Long.valueOf(target.indexOf(String.valueOf(c))) + 1;

			for (String s : CodeBeanList.symbolList) {
				// 当前迭代次数， 上面已经默认迭代+1
				Long number = 1L;
				// 用于存储临时的字符串
				final StringBuffer buffer = new StringBuffer();
				if (s.equals(String.valueOf(c))) {
//					number++;
//					nIndex = cIndex + number;
					// 如果下一个是标点符号， 则继续
					while (-1 != CodeBeanList.symbolList.indexOf(chars[nIndex.intValue()])) {
						buffer.append(chars[nIndex.intValue()]);
						number++;
						nIndex = cIndex + number;
					}
				}

				// 如果迭代次数>1, 就表明有2个以上连续标点
				if (number > 2) {
					target = target.replace(buffer.toString(), String.valueOf(chars[cIndex.intValue()]));
				}
			}
		}
		return target;

	}
}
