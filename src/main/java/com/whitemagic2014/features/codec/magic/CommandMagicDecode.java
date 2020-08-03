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
 * @ClassName: CommandDecode
 * @Description: 朝代解密
 * @author: chenhaoyu
 * @date: Jul 21, 2020 6:01:42 PM
 */
public class CommandMagicDecode extends BaseCommand implements GroupCommand {

	@Override
	public CommandProperties properties() {
		return new CommandProperties("解密", "帮我解密");
	}

	@Override
	public String groupMessage(EventGroupMessage event, GroupUser sender, Group group, String command,
			ArrayList<String> args) {
		String result = "";
		result += "@" + getGroupNameNoCache(event,sender) + " ";
		result += "已经解密完啦,明文: \n";

		String origin = getOriginText(event, command);
		result += MagicEncode.decode(origin, null);
		return result;
	}

}
