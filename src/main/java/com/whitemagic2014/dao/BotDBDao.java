package com.whitemagic2014.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.whitemagic2014.pojo.BotDB;

/**
 * 
 * @ClassName: BotDBDao
 * @Description:db相关 比如创建表 之类的
 * @author: chenhaoyu
 * @date: Jul 27, 2020 11:50:16 AM
 * @Copyright
 */
public interface BotDBDao {

	/**
	 * 
	 * @Description: 获得DB版本信息
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 27, 2020 4:41:43 PM
	 */
	BotDB DBVersion();

	/**
	 * 
	 * @Description: 更新db版本
	 * @param db
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 27, 2020 4:57:03 PM
	 */
	int updateDBVersion(BotDB db);

	/**
	 * 
	 * @Description: 检查表是否存在
	 * @param tableName
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 27, 2020 4:41:50 PM
	 */
	Boolean checkTableExist(@Param("tableName") String tableName);

	/**
	 * 
	 * @Description:获得所有表名
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 27, 2020 4:42:02 PM
	 */
	List<String> tables();

	/**
	 * 
	 * @Description: 直接执行ddl sql
	 * @param sql
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 27, 2020 5:50:10 PM
	 */
	int runDDLSql(@Param("sql") String sql);
}
