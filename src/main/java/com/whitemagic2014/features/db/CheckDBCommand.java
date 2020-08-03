package com.whitemagic2014.features.db;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whitemagic2014.dao.BotDBDao;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;

/**
 * 
 * @ClassName: CheckDBCommand
 * @Description: 查询数据库表信息
 * @author: chenhaoyu
 * @date: Jul 27, 2020 4:41:17 PM
 */
@Component
public class CheckDBCommand implements EverywhereCommand {
	@Autowired
	BotDBDao dbDao;

	@Override
	public CommandProperties properties() {
		return new CommandProperties("checkDB");
	}

	@Override
	public String run(EventMessage event, User sender, String command, ArrayList<String> args) {
		if (sender.getId() != 418379149L) {
			return "抱歉,你没有权限执行此操作";
		}
		if (args.size() >= 1) {
			String tableName = args.get(0);
			if (dbDao.checkTableExist(tableName)) {
				return "表 [" + tableName + "] 存在";
			} else {
				return "表 [" + tableName + "] 不存在";
			}
		} else {
			return "数据库信息: " + dbDao.DBVersion().toString() + "\n" + "已有表: "
					+ dbDao.tables().stream().map(s -> "[" + s + "] ").reduce("", String::concat);
		}
	}

}
