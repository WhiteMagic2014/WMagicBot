package com.whitemagic2014.features.codec;

import static cc.moecraft.utils.StringCodecUtils.fromAscii;
import static cc.moecraft.utils.StringCodecUtils.fromBase32;
import static cc.moecraft.utils.StringCodecUtils.fromHex;

import java.util.ArrayList;

import com.whitemagic2014.commands.BaseCommand;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.GroupCommand;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.user.Group;
import cc.moecraft.icq.user.GroupUser;
import cc.moecraft.utils.ArrayUtils;

/**
 * 此类由 Hykilpikonna 在 2018/06/13 创建! Created by Hykilpikonna on 2018/06/13!
 * Github: https://github.com/hykilpikonna QQ: admin@moecraft.cc -OR- 871674895
 *
 * @author Hykilpikonna
 */
public class CommandDecode extends BaseCommand implements GroupCommand {

	@Override
	public CommandProperties properties() {
		return new CommandProperties("decode");
	}

	@Override
	public String groupMessage(EventGroupMessage event, GroupUser sender, Group group, String command,
			ArrayList<String> args) {
		if (args.size() < 2)
			return "[指令前缀][decode] [类型] [加密的字符串] || 类型可能为: " + CommandEncode.MESSAGE_POSSIBLE_TYPES;

		String text;
		String encoded = ArrayUtils.getTheRestArgsAsString(args, 1);

		switch (args.get(0).toLowerCase()) {
		case "hex":
			text = fromHex(encoded);
			break;
		case "b32":
		case "base32":
			text = fromBase32(encoded);
			break;
		case "ascii":
			text = fromAscii(encoded);
			break;
		default:
			return "不支持的类型, 类型可能为: " + CommandEncode.MESSAGE_POSSIBLE_TYPES;
		}
		return getGroupNameNoCache(event,sender) + " >> " + text;
	}
}
