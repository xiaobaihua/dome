package com.wd.utils;

import com.alibaba.excel.util.FileUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wd.bean.ProxyHttp;
import com.wd.bean.User;
import okhttp3.*;
import org.junit.Test;

import java.io.*;
import java.nio.CharBuffer;
import java.util.ArrayList;

/**
 * @author xbh
 * @date
 * @Description
 */
public class dome {

	@Test
	public void a() {
		final String s = MyStringUtils.addLastSymbolI("String。。。。。");
		System.out.println(s);
	}


	@Test
	public void readJson() {
		final ArrayList<ProxyHttp> proxys = new ArrayList<>();
		try {
			final FileReader reader = new FileReader("ProxyList.json");
			char[] cs = new char[1024];

			StringBuffer stringBuffer = new StringBuffer();
			int len = 0;
			while (-1 != (len = reader.read(cs))) {
				stringBuffer.append(cs);
			}

			final String[] strings = stringBuffer.toString().split("\n");
			for (String string : strings) {
				System.out.println(string);
				final ProxyHttp object = JSON.parseObject(string, ProxyHttp.class);
				proxys.add(object);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}


		System.out.println(proxys);
	}

	@Test
	public void name() {
		Writer writer = null;
		try {
			final OkHttpClient client = new OkHttpClient();
			final Request request = new Request.Builder()
				.url("https://raw.githubusercontent.com/fate0/proxylist/master/proxy.list")
				.build();

			Response response = null;
			final Call call = client.newCall(request);
			response = call.execute();

			if (response.isSuccessful()) {
				final ResponseBody body = response.body();
				writer = new FileWriter("ProxyList.json");
				writer.write(body.string());
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
}
