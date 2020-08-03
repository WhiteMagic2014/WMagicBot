package com.whitemagic2014.db;

import static cc.moecraft.logger.format.AnsiColor.GREEN;
import static cc.moecraft.logger.format.AnsiColor.RED;
import static cc.moecraft.logger.format.AnsiColor.YELLOW;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.whitemagic2014.dao.BotDBDao;
import com.whitemagic2014.pojo.BotDB;
import com.whitemagic2014.pojo.DBVersionTable;
import com.whitemagic2014.pojo.Version;
import com.whitemagic2014.util.BotLogger;

import cc.moecraft.logger.HyLogger;

/**
 * 
 * @ClassName: DBVersion
 * @Description: 数据库更新
 * @author: chenhaoyu
 * @date: Jul 27, 2020 5:05:06 PM
 * @Copyright
 */
@Component
public class DBVersion {

	@Autowired
	BotDBDao dbDao;

	@Autowired
	@Qualifier("dbversionList")
	List<DBVersionTable> dbVersionTable;

	public void checkUpdateDB() {
		HyLogger logger = BotLogger.getInstance().getLogger("SqlLite");
		logger.log(String.format("%s 开始校验数据版本!", YELLOW));
		if (dbVersionTable.isEmpty()) {
			logger.warning(String.format("%s 获取最新版本失败!", RED));
		} else {
			BotDB db = dbDao.DBVersion();
			Version ver = new Version(db.getVersion());
			Version latestVer = dbVersionTable.get(dbVersionTable.size() - 1).getVer();
			logger.log(String.format("%s 当前版本:" + ver + ",最新版本:" + latestVer, GREEN));

			if (ver.compareTo(latestVer) < 0) {
				logger.log(String.format("%s 开始更新!", YELLOW));

				for (DBVersionTable dbver : dbVersionTable) {
					if (ver.compareTo(dbver.getVer()) < 0) {
						for (String sql : dbver.getSqls()) {
							dbDao.runDDLSql(sql);
						}
					} else {
						continue;
					}
				}
				db.setVersion(latestVer.toString());
				dbDao.updateDBVersion(db);
				logger.log(String.format("%s 更新完毕!", GREEN));
			}
		}

	}

}
