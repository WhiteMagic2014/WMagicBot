package com.whitemagic2014.pojo;

import java.util.List;

/**
 * 
 * @ClassName: DBVersionTable
 * @Description: 某版本 自更新db table的sql
 * @author: chenhaoyu
 * @date: Jul 27, 2020 5:13:38 PM
 * @Copyright
 */
public class DBVersionTable {

	/**
	 * 当前数据库版本
	 */
	Version ver;

	/**
	 * 更新至此版本需要执行sql
	 */
	List<String> sqls;

	public Version getVer() {
		return ver;
	}

	public void setVer(Version ver) {
		this.ver = ver;
	}

	public List<String> getSqls() {
		return sqls;
	}

	public void setSqls(List<String> sqls) {
		this.sqls = sqls;
	}

}
