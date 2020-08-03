package com.whitemagic2014.listeners;

import cc.moecraft.icq.event.EventHandler;
import cc.moecraft.icq.event.IcqListener;
import cc.moecraft.icq.event.events.meta.EventMetaHeartbeat;

public class HeartListener extends IcqListener {
	
	
	@EventHandler
    public void onPMEvent(EventMetaHeartbeat event)
    {
        System.out.println("接到心跳:"+event.getStatus());
        System.out.println(event);
    
    }
}
