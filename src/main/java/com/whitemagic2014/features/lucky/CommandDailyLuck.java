package com.whitemagic2014.features.lucky;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.whitemagic2014.commands.BaseCommand;
import com.whitemagic2014.util.MagicGacha;
import com.whitemagic2014.util.MagicMd5;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.GroupCommand;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.user.Group;
import cc.moecraft.icq.user.GroupUser;

/**
 * 
 * @ClassName: CommandDailyLuck
 * @Description:每日抽签
 * @author: chenhaoyu
 * @date: Jul 23, 2020 11:00:20 PM
 */
public class CommandDailyLuck extends BaseCommand implements GroupCommand {

	MagicGacha dailyGacha;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public CommandDailyLuck() {
		Map<String, Integer> itemAndRate = new HashMap<String, Integer>();
		itemAndRate.put("大吉 恭喜你! 万事如意 百无禁忌 不去买个彩票吗?欧皇 ", 500);
		itemAndRate.put("中吉 恭喜拉! 抽卡应该能见彩咯~ ", 1500);
		itemAndRate.put("小吉 恭喜！刷困难本也许能出3碎片 走起！", 1500);
		itemAndRate.put("吉 小小的幸运 留意脚下 没准捡到硬币了呢~", 2500);
		itemAndRate.put("末吉 平平淡淡才是真 健康快乐每一天", 2000);
		itemAndRate.put("凶 风水轮流转 没准明天就欧了呢?", 1500);
		itemAndRate.put("大凶 啊这...给你换一个吧...\n恭喜你 抽到了特殊签! 姬吉!!!", 500);
		dailyGacha = new MagicGacha(itemAndRate);
	}

	@Override
	public CommandProperties properties() {
		return new CommandProperties("抽签");
	}

	@Override
	public String groupMessage(EventGroupMessage event, GroupUser sender, Group group, String command,
			ArrayList<String> args) {
		String seed = sender.getId() + sdf.format(new Date());
		String item = dailyGacha.gacha(MagicMd5.getGachaRate(seed));
		return "@" + getGroupNameNoCache(event, sender) + " \n抽到了 " + item;
	}

}
