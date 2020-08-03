package com.whitemagic2014.bean;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.whitemagic2014.bot.MagicBot;
import com.whitemagic2014.commands.CommandTest;
import com.whitemagic2014.db.DBInitHelper;
import com.whitemagic2014.db.DBVersion;
import com.whitemagic2014.features.annoy.AnnoyingListener;
import com.whitemagic2014.features.annoy.CommandAnnoy;
import com.whitemagic2014.features.antirecall.AntiRecallListener;
import com.whitemagic2014.features.antirecall.CommandAntiRecall;
import com.whitemagic2014.features.antirecall.CommandAntiRecallOut;
import com.whitemagic2014.features.caneat.CanEatCommand;
import com.whitemagic2014.features.codec.CommandDecode;
import com.whitemagic2014.features.codec.CommandEncode;
import com.whitemagic2014.features.codec.magic.CommandMagicDecode;
import com.whitemagic2014.features.codec.magic.CommandMagicEncode;
import com.whitemagic2014.features.db.CheckDBCommand;
import com.whitemagic2014.features.lucky.CommandDailyLuck;
import com.whitemagic2014.features.plan.PlanCommand;
import com.whitemagic2014.features.roll.RollCommand;
import com.whitemagic2014.listeners.ExceptionListener;
import com.whitemagic2014.pojo.BotAccountData;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.command.interfaces.IcqCommand;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.sender.IcqHttpApi;

/**
 * 
 * @ClassName: GlobalBot
 * @Description: 全局bot
 * @author: chenhaoyu
 * @date: Jul 24, 2020 11:24:28 AM
 */
@Configuration
public class GlobalBot {

	@Autowired
	CommandTest tc;

	@Autowired
	CheckDBCommand ctc;
	
	@Autowired
	PlanCommand pc;
	
	@Autowired
	CanEatCommand cec;
	
	@Autowired
	RollCommand rc;
	
	@Autowired
	DBVersion dbVersion;
	
	
	
	/**
	 * 
	 * @Description: 初始化指令前缀
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 24, 2020 11:56:56 AM
	 */
	@Bean(name = "initCommandStr")
	public String[] initCommandStr() {
		String[] commandStr = new String[] { "bot", "!", "~", "#", "二号机", "！" };
		return commandStr;
	}

	/**
	 * 
	 * @Description: 初始化时间监听
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 24, 2020 11:57:05 AM
	 */
	@Bean(name = "initListeners")
	public IcqListener[] initListeners() {
		IcqListener[] listeners = new IcqListener[] { new AntiRecallListener(), new AnnoyingListener(),
				new ExceptionListener() };
		return listeners;
	}

	/**
	 * 
	 * @Description: 初始化指令
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 24, 2020 11:57:14 AM
	 */
	@Bean(name = "initCommands")
	public IcqCommand[] initCommands() {
		IcqCommand[] commands = new IcqCommand[] { new CommandMagicDecode(), new CommandMagicEncode(),
				new CommandAnnoy(), new CommandAntiRecall(), new CommandAntiRecallOut(), new CommandEncode(),
				new CommandDecode(), new CommandDailyLuck(),tc,ctc,pc,cec,rc};

		return commands;
	}


	
	/**
	 * 
	 * @Description:spring 管理的全局bot
	 * @param commandStr
	 * @param commands
	 * @param listeners
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 30, 2020 4:09:28 PM
	 */
	@Bean(name = "MagicBot")
	public PicqBotX initBot(@Qualifier("initCommandStr") String[] commandStr,
			@Qualifier("initCommands") IcqCommand[] commands, @Qualifier("initListeners") IcqListener[] listeners) {
		
		
//		MagicBot bot = new MagicBot("MagicBotDebug", 28282, Arrays.asList(new BotAccountData("二号机本机测试", "10.104.101.200", 25700)), true, commandStr, commands,listeners);// debug
		
		MagicBot bot = new MagicBot("MagicBot", 28282, Arrays.asList(new BotAccountData("二号机", "cqhttp", 5700)), false, commandStr , commands,listeners);// 打包用

		// 数据库check初始化
		DBInitHelper.getInstance().initDBIfNotExist();
		// 数据库版本检查更新
		dbVersion.checkUpdateDB();
		return bot.getBot();
	}

	/**
	 * 
	 * @Description:获得默认的IcqHttpApi 如果有多个机器人账号 如果有多个账号, 可以用
	 *                              bot.getAccountManager().getAccounts() 获取账号列表循环一下
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 24, 2020 11:42:42 AM
	 */
	@Bean
	public IcqHttpApi getDefaultIcqHttpApi(@Qualifier("MagicBot") PicqBotX bot) {
		return bot.getAccountManager().getNonAccountSpecifiedApi();
	}

}
