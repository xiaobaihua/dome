package com.wd.utils;

import org.junit.Test;

import java.util.List;

/**
 * @author xbh
 * @date 2019年6月11日11:45:46
 * @Description
 */
public class FilterString {
	String s = "aaaaaaaaaaaaaaaaaaaaaaaaaaaa";


	@Test
	public void tilteString() {
		System.out.println(resultJieQu(s));
	}

//	@Test
//	public void tilteString(List<WdBean> beans) {
//		System.out.println(s);
//	}

	protected String resultJieQu(String s) {
		final List<String> resList = CodeBeanList.reusltTianChongList;
		String p = "";

//		// 大于130， 缩小
//		if (s.length() > 130) {
//			final String[] strings = s.split("。");
//			for (int i = 0; i < strings.length; i++) {
//				if (p.length() < 100) {
//					p += strings[i];
//				}
//			}
//			return p;
//		}

//		// 小于100， 填充
//		final ArrayList<Integer> list = new ArrayList<>();
//		p = s;
//		while (p.length() < 100 && !s.equals("") && s != null){
//			final Integer i = new Random().nextInt(resList.size());
//			if (list.indexOf(i) == -1) {
//				list.add(i);
//				p = p + resList.get(i);
//				if (p.length() > 100) {
//					return p;
//				}
//			}
//			System.out.println(p.length());
//		}
		return p;
	}
}
