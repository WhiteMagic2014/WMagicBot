package com.whitemagic2014.features.caneat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.whitemagic2014.dao.CanEatDao;
import com.whitemagic2014.pojo.CanEat;

import cc.moecraft.icq.command.CommandProperties;
import cc.moecraft.icq.command.interfaces.EverywhereCommand;
import cc.moecraft.icq.event.events.message.EventMessage;
import cc.moecraft.icq.user.User;

@Component
public class CanEatCommand implements EverywhereCommand{

	@Autowired
	CanEatDao ced;
	
	@Override
	public CommandProperties properties() {
		 return new CommandProperties("能吃", "可以吃", "吃");
	}

	@Override
	public String run(EventMessage event, User sender, String command, ArrayList<String> args) {
		if (sender.getId() != 418379149L) {
			return "这个功能不对所有人开放";
		}
		if (args.get(0).equals("记录") || args.get(0).equals("创建") ) {
			List<CanEat>  ceList = ced.findByName(args.get(1).trim());
			if (!ceList.isEmpty()) {
				return "已经记录";
			}
			CanEat ce = new CanEat();
			ce.setItemName(args.get(1).trim());
			if (args.get(2).contains("不")) {
				ce.setCan(false);
			}else {
				ce.setCan(true);
			}
			ced.insert(ce);
			return "已经记录";
		}else {
			List<CanEat>  ceList = ced.findByName(args.get(0).trim());
			if (ceList.isEmpty()) {
				return "还没记录 " + args.get(0).trim() +" 能不能吃 百度之后记得记录一下";
			}
			if (ceList.get(0).getCan()) {
				return "能吃";
			}else {
				return "不能吃";
			}
		}
	}

}
