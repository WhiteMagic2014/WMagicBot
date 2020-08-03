package com.whitemagic2014.commands;

import org.apache.commons.lang3.StringUtils;

import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.sender.returndata.returnpojo.get.RGroupMemberInfo;
import cc.moecraft.icq.user.GroupUser;

/**
 * 
 * @ClassName: BaseCommand
 * @Description: 所有command父类 通用方法
 * @author: chenhaoyu
 * @date: Jul 22, 2020 3:17:00 PM
 */
public class BaseCommand {

	protected String getOriginText(EventMessage event, String command) {
		String txt = event.getMessage();
		String origin = txt.substring(txt.indexOf(command) + command.length(), txt.length()).trim();
		return origin;
	}

	/**
	 * 
	 * @Description: 获得群名片
	 * @param sender
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 24, 2020 10:35:07 AM
	 */
	protected String getGroupName(GroupUser sender) {
		// 群名片
		String name = sender.getInfo().getCard();
		if (StringUtils.isEmpty(name)) {
			return sender.getInfo().getNickname();
		} else {
			return name;
		}
	}

	/**
	 * 
	 * @Description:获得群名片 不用缓存
	 * @param event
	 * @param sender
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 24, 2020 11:05:20 AM
	 */
	public String getGroupNameNoCache(EventGroupMessage event, GroupUser sender) {
		RGroupMemberInfo info = event.getHttpApi().getGroupMemberInfo(sender.getGroup().getId(), sender.getId())
				.getData();
		String name = info.getCard();
		if (StringUtils.isEmpty(name)) {
			return info.getNickname();
		} else {
			return name;
		}
	}

}
