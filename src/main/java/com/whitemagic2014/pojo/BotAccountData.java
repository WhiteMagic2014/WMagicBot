package com.whitemagic2014.pojo;

/**
 * 
 * @ClassName: BotAccountData
 * @Description: 用来初始化 bot账号 的数据
 * @author: chenhaoyu
 * @date: Jul 24, 2020 12:00:06 PM
 */
public class BotAccountData {

	/**
	 * 机器人账户名
	 */
	String name;
	/**
	 * cqhttp url
	 */
	String cqhttpUrl;

	/**
	 * cqhttp 端口
	 */
	int cqhttpPort;

	public BotAccountData() {
		super();
	}

	public BotAccountData(String name, String cqhttpUrl, int cqhttpPort) {
		this.name = name;
		this.cqhttpUrl = cqhttpUrl;
		this.cqhttpPort = cqhttpPort;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCqhttpUrl() {
		return cqhttpUrl;
	}

	public void setCqhttpUrl(String cqhttpUrl) {
		this.cqhttpUrl = cqhttpUrl;
	}

	public int getCqhttpPort() {
		return cqhttpPort;
	}

	public void setCqhttpPort(int cqhttpPort) {
		this.cqhttpPort = cqhttpPort;
	}

}
