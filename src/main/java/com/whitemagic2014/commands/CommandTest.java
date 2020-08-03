package com.whitemagic2014.commands;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whitemagic2014.dao.BotDBDao;
import com.whitemagic2014.dao.Testdao;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;

@Component
public class CommandTest implements EverywhereCommand {
	@Autowired
	Testdao td;

	@Autowired
	BotDBDao dbDao;

	@Override
	public CommandProperties properties() {
		return new CommandProperties("test", "测试", "测试2");
	}

	@Override
	public String run(EventMessage event, User sender, String command, ArrayList<String> args) {

		String sql =  "create table testtable(" 
				+ "id INTEGER PRIMARY KEY autoincrement,"
				+ "name varchar(255));";
		
		dbDao.runDDLSql(sql);
		return "指令已经执行";

	}
}
