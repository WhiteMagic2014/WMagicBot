package com.whitemagic2014.features.codec.magic;

import java.util.ArrayList;

import com.whitemagic2014.commands.BaseCommand;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.GroupCommand;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.user.Group;
import cc.moecraft.icq.user.GroupUser;

/**
 * 
 * @ClassName: CommandEncode
 * @Description: 朝代加密
 * @author: chenhaoyu
 * @date: Jul 21, 2020 5:59:51 PM
 */
public class CommandMagicEncode extends BaseCommand implements GroupCommand {

	@Override
	public CommandProperties properties() {
		return new CommandProperties("加密", "帮我加密");
	}

	@Override
	public String groupMessage(EventGroupMessage event, GroupUser sender, Group group, String command,
			ArrayList<String> args) {

		String result = "";
		result += "@" + getGroupNameNoCache(event,sender) + " ";
		result += "已经加密完啦,密文: \n";

		String origin = getOriginText(event, command);
		result += MagicEncode.encode(origin, null);
		return result;
	}

}
