package com.whitemagic2014.util;

import cc.moecraft.logger.HyLogger;
import cc.moecraft.logger.LoggerInstanceManager;

/**
 * 
 * @ClassName: BotLogger
 * @Description: 由于bot项目中的logger比较特殊，所以在初始化bot的时候 将其做成全局单例logger
 * @author: chenhaoyu
 * @date: Jul 23, 2020 6:00:10 PM
 * @Copyright
 */
public class BotLogger {

	// 内部静态类构造单例
	private BotLogger() {
	}

	private static class Lazy {
		private static final BotLogger instsance = new BotLogger();
	}

	public static final BotLogger getInstance() {
		return Lazy.instsance;
	}
	// end

	/**
	 * Logger实例管理器
	 */
	private LoggerInstanceManager loggerInstanceManager;

	/**
	 * 是否debug模式
	 */
	private boolean debug;
	/**
	 * Logger
	 */
	private HyLogger logger;

	/**
	 * 
	 * @Description:获得默认的logger实例 PicqBotX
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 23, 2020 6:05:47 PM
	 */
	public HyLogger getLogger() {
		return logger;
	}

	/**
	 * 
	 * @Description: 获得自定义的logger实例
	 * @param prefix
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 23, 2020 6:08:08 PM
	 */
	public HyLogger getLogger(String prefix) {
		return loggerInstanceManager.getLoggerInstance(prefix, debug);
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public void setLogger(HyLogger logger) {
		this.logger = logger;
	}

	public void setLoggerInstanceManager(LoggerInstanceManager manager) {
		this.loggerInstanceManager = manager;
	}

}
