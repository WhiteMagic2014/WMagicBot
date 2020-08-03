package com.whitemagic2014.db;

import static cc.moecraft.logger.format.AnsiColor.GREEN;
import static cc.moecraft.logger.format.AnsiColor.RED;
import static cc.moecraft.logger.format.AnsiColor.YELLOW;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.whitemagic2014.util.BotLogger;
import com.whitemagic2014.util.Path;

import cc.moecraft.logger.HyLogger;

/**
 * 
 * @ClassName: DBInitHelper
 * @Description: 数据库初始化工具
 * @author: chenhaoyu
 * @date: Jul 23, 2020 4:20:09 PM
 * @Copyright
 */
public class DBInitHelper {

	private HyLogger logger = BotLogger.getInstance().getLogger("SqlLite");

	// 内部静态类构造单例
	private DBInitHelper() {
	}

	private static class Lazy {
		private static final DBInitHelper instsance = new DBInitHelper();
	}

	public static final DBInitHelper getInstance() {
		return Lazy.instsance;
	}
	// end

	/**
	 * 
	 * @Description:如果还没有初始化数据库，则初始化数据库
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 23, 2020 5:41:27 PM
	 */
	public boolean initDBIfNotExist() {
		File db = new File(Path.getPath() + "botData.db");
		if (db.exists()) {
			logger.log(String.format("%s 已经找到数据文件!", GREEN));
			return false;
		} else {
			logger.log(String.format("%s 未找到数据文件,即将初始化数据文件!", YELLOW));
			createDB();
			logger.log(String.format("%s 已创建数据文件:" + Path.getPath() + "botData.db", GREEN));
			return true;
		}
	}

	/**
	 * 
	 * @Description: 创建数据库文件
	 * @author: chenhaoyu
	 * @time:Jul 27, 2020 11:38:30 AM
	 */
	private void createDB() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e1) {
			logger.error(String.format("%s "+e1.getMessage(), RED ));
		}
		String url = "jdbc:sqlite:" + Path.getPath() + "botData.db";

		try (Connection connection = DriverManager.getConnection(url);
				Statement statement = connection.createStatement();) {

			statement.executeUpdate("DROP TABLE IF EXISTS DBInfo;");

			String createDBsql = "create table DBInfo(" 
					+ "id INTEGER PRIMARY KEY autoincrement,"
					+ "name varchar(255) ," 
					+ "version varchar(20)," 
					+ "createDate varchar(20),"
					+ "updateDate varchar(20));";
			statement.executeUpdate(createDBsql);

			String initsql = "INSERT INTO DBInfo (name,version,createDate,updateDate) VALUES ("
					+ "'MagicBot'," 
					+ "'0.0.1'," 
					+ "'" + sdf.format(new Date()) + "'," 
					+ "'')";
			statement.executeUpdate(initsql);

		} catch (Exception e) {
			logger.error(String.format("%s "+e.getMessage(), RED ));
		}
	}

}
