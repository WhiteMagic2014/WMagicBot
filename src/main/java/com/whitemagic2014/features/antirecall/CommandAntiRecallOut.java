package com.whitemagic2014.features.antirecall;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.GroupCommand;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.user.Group;
import cc.moecraft.icq.user.GroupUser;

import java.util.ArrayList;

/**
 * 
 * @ClassName: CommandAntiRecallOut
 * @Description:防止撤回超强版本 
 * [指令前缀][arout/防撤回+] 				: 查看记录的所有消息
 * [指令前缀][arout/防撤回+] [x] [y]	: 查看x至y条记录
 * @author: chenhaoyu
 * @date: Jul 22, 2020 4:35:16 PM
 */
public class CommandAntiRecallOut implements GroupCommand {
	@Override
	public String groupMessage(EventGroupMessage event, GroupUser sender, Group group, String command,
			ArrayList<String> args) {
		
		if (args.get(0).equalsIgnoreCase("help")) {
			String help = "防止撤回超强版本:\n" + 
					"[指令前缀][arout/防撤回+] 				: 查看记录的所有消息\n" + 
					"[指令前缀][arout/防撤回+] [x] [y]	: 查看x至y条记录";
			return help;
		}
		
		if (args.size() == 0) {
			StringBuilder ultimateMessage = new StringBuilder();

			for (String message : AntiRecallListener.groupTexts.get(group.getId()))
				ultimateMessage.append(message).append("\n");

			return ultimateMessage.toString();
		}
		if (args.size() == 2) {
			StringBuilder ultimateMessage = new StringBuilder();

			try {
				int from = Integer.parseInt(args.get(0));
				int to = Integer.parseInt(args.get(1));

				ArrayList<String> messageList = AntiRecallListener.groupTexts.get(group.getId());

				for (int i = from; i < to; i++)
					ultimateMessage.append(messageList.get(i)).append("\n");

				return ultimateMessage.toString();
			} catch (NumberFormatException ignored) {

			} catch (Exception e) {
				e.printStackTrace();
				return "未知错误, 见后台";
			}
		}

		return "[指令前缀][arout/防撤回+] [help]";
	}

	@Override
	public CommandProperties properties() {
		return new CommandProperties("arout", "防撤回+");
	}
}
