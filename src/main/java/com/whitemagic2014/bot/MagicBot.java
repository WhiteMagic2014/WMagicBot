package com.whitemagic2014.bot;

import static cc.moecraft.logger.format.AnsiColor.GREEN;
import static cc.moecraft.logger.format.AnsiColor.YELLOW;

import java.util.List;

import com.whitemagic2014.listeners.SimpleTextLoggingListener;
import com.whitemagic2014.pojo.BotAccountData;
import com.whitemagic2014.util.BotLogger;

import cc.moecraft.icq.PicqBotX;
import cc.moecraft.icq.PicqConfig;
import cc.moecraft.icq.command.interfaces.IcqCommand;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.logger.HyLogger;

public class MagicBot {

	private PicqBotX bot;

	public MagicBot(String botName, int localPort, List<BotAccountData> datas, boolean debug, String[] commandStr,
			IcqCommand[] commands, IcqListener[] listeners) {
		bot = startBot(botName, localPort, datas, debug, commandStr, commands, listeners);
	}

	public PicqBotX getBot() {
		return bot;
	}

	public void setBot(PicqBotX bot) {
		this.bot = bot;
	}

	/**
	 * 
	 * @Description: 启动bot,全局只有一个bot ,bot中 可以有多个account
	 * @param botName   bot的名字
	 * @param localPort bot占用本机端口
	 * @param datas     bot需要注册的机器人账号
	 * @param debug     是否开启debug模式
	 * @author: chenhaoyu
	 * @time:Jul 24, 2020 12:10:53 PM
	 */
	private PicqBotX startBot(String botName, int localPort, List<BotAccountData> datas, boolean debug,
			String[] commandStr, IcqCommand[] commands, IcqListener[] listeners) {

		PicqConfig config = new PicqConfig(localPort);
		config.setDebug(debug);
		config.setLogFileName(botName);

		PicqBotX bot = new PicqBotX(config);
		HyLogger logger = bot.getLogger();
		// 设置全局的logger
		BotLogger.getInstance().setDebug(debug);
		BotLogger.getInstance().setLogger(logger);
		BotLogger.getInstance().setLoggerInstanceManager(bot.getLoggerInstanceManager());

		for (BotAccountData data : datas) {
			// 注册失败需要重新尝试
			boolean tryAgain = true;
			while (tryAgain) {
				try {
					bot.addAccount(data.getName(), data.getCqhttpUrl(), data.getCqhttpPort());
					logger.log(String.format("%s" + botName + " 已经注册机器人账号:" + data.getName(), GREEN));
					tryAgain = false;
				} catch (Exception e) {
					logger.log(String
							.format("%s" + botName + " 尝试连接账号 " + data.getName() + " cqhttp失败 20秒后再次尝试连接,请登录[http://"
									+ data.getCqhttpUrl() + ":" + data.getCqhttpPort() + "]确保酷Q已经启动", YELLOW));
					if (tryAgain) {
						try {
							Thread.sleep(20000L);
						} catch (InterruptedException e2) {
							// 重启间隔
						}
					}
				}
			}
		}

		if (listeners.length > 0) {
			bot.getEventManager().registerListeners(listeners);
		}
		if (!debug) {
			// 在没有Debug的时候加上这个消息日志监听器,log中记录对话记录
			bot.getEventManager().registerListener(new SimpleTextLoggingListener());
		}
		if (commandStr.length > 0) {
			bot.enableCommandManager(commandStr);
		}
		if (commands.length > 0) {
			bot.getCommandManager().registerCommands(commands);
		}
		bot.startBot();// 启动机器人, 不会占用主线程
		logger.log(String.format("%s" + botName + " 启动成功!", GREEN));

		return bot;
	}

}
