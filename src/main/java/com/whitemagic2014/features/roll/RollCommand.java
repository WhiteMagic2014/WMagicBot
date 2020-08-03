package com.whitemagic2014.features.roll;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.stereotype.Component;

import com.whitemagic2014.commands.BaseCommand;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.GroupCommand;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.user.Group;
import cc.moecraft.icq.user.GroupUser;

/**
 * 
 * @ClassName: RollCommand
 * @Description: roll点功能
 * @author: chenhaoyu
 * @date: Jul 30, 2020 5:09:08 PM
 * @Copyright
 */
@Component
public class RollCommand extends BaseCommand implements GroupCommand {

	@Override
	public CommandProperties properties() {
		return new CommandProperties("roll");
	}

	@Override
	public String groupMessage(EventGroupMessage event, GroupUser sender, Group group, String command,
			ArrayList<String> args) {

		Random random = new Random();
		if (args.isEmpty()) {
			return "@" + getGroupName(sender) + " roll出 " + (random.nextInt(100) + 1);
		} else if (args.size() == 1) {

			int range = 0;
			try {
				range = Integer.valueOf(args.get(0));
			} catch (NumberFormatException e) {
				return "指令错误: [指令前缀][roll] [数字(可省略)]";
			}
			if (range > Integer.MAX_VALUE) {
				return "指令错误: 数字范围 0 ~ " + Integer.MAX_VALUE;
			}
			return "@" + getGroupName(sender) + " roll出 " + (random.nextInt(range) + 1);
		} else {
			return "指令错误: [指令前缀][roll] [数字(可省略)]";
		}
	}

}
