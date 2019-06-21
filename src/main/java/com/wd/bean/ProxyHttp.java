package com.wd.bean;

import java.util.List;

/**
 * @author xbh
 * @date 2019年6月13日18:22:50
 * @Description http代理
 */
public class ProxyHttp {
	private String country;
	private Integer response_time;
	private String host;
	private String anonymity;
	private String from;
	private Integer port;
	private String type;
	private List<String> export_address;

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Integer getResponse_time() {
		return response_time;
	}

	public void setResponse_time(Integer response_time) {
		this.response_time = response_time;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getAnonymity() {
		return anonymity;
	}

	public void setAnonymity(String anonymity) {
		this.anonymity = anonymity;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getExport_address() {
		return export_address;
	}

	public void setExport_address(List<String> export_address) {
		this.export_address = export_address;
	}
}
