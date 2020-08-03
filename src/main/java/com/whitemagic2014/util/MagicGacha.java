package com.whitemagic2014.util;

import java.util.Map;

/**
 * 
 * @ClassName: MagicGacha
 * @Description:通用抽奖模板
 * @author: chenhaoyu
 * @date: Jul 23, 2020 10:56:09 PM
 * @Copyright
 */
public class MagicGacha {

	/**
	 * 道具名-概率，概率0~10000 对应0%~100%
	 */
	Map<String, Integer> itemAndRate;

	public MagicGacha(Map<String, Integer> itemAndRate) {
		this.itemAndRate = itemAndRate;
	}

	public String gacha(int rate) {
		int area = 0;
		String result = "";
		for (String item : itemAndRate.keySet()) {
			Integer rise = itemAndRate.get(item);
			if (area <= rate && rate < area + rise) {
				result = item;
				break;
			} else {
				area += rise;
			}
		}
		return result;
	}

}
