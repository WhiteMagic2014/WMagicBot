package com.whitemagic2014.listeners;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.message.EventGroupMessage;
import cc.moecraft.icq.event.events.message.EventPrivateMessage;

public class RobListener extends IcqListener {

	@EventHandler
	public void privateMessage(EventPrivateMessage event) {
		String msg = event.getMessage();
		if (msg.startsWith("复读机")) {
			event.respond(event.getMessage().replace("复读机", ""));
		}
	}

	@EventHandler
	public void groupMessage(EventGroupMessage event) {
		String msg = event.getMessage();
		if (msg.startsWith("复读机")) {
			event.respond(event.getMessage().replace("复读机", ""));
		}
	}

}
