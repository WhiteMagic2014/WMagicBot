package com.whitemagic2014.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @ClassName: MagicMd5
 * @Description: 字符转md5 扩展
 * @author: chenhaoyu
 * @date: Jul 23, 2020 10:26:31 PM
 * @Copyright
 */
public class MagicMd5 {

	/**
	 * 
	 * @Description:根据输入的字符获得其hash 暂时没想到用来干啥
	 * @param str
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 23, 2020 10:24:14 PM
	 */
	public static String getLuckyString(String str) {
		return byteToString(getMD5Byte(str));
	}

	/**
	 * 
	 * @Description:根据输入的字符获得其hash的数值 可以用来做种子随机
	 * @param str
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 23, 2020 10:24:19 PM
	 */
	public static int getLuckyInt(String str) {
		return bytes2Int(getMD5Byte(str));
	}

	/**
	 * 
	 * @Description:获得gacha用的rate
	 * @param str
	 * @return
	 * @author: chenhaoyu
	 * @time:Jul 23, 2020 11:54:01 PM
	 */
	public static int getGachaRate(String str) {
		return Math.abs(bytes2Int(getMD5Byte(str))) % 10000;
	}

	private static final String[] strDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	private static String byteToArrayString(byte bytes) {
		int iRet = bytes;
		if (iRet < 0) {
			iRet += 256;
		}
		int iD1 = iRet / 16;
		int iD2 = iRet % 16;
		return strDigits[iD1] + strDigits[iD2];
	}

	private static String byteToString(byte[] bytes) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			sBuffer.append(byteToArrayString(bytes[i]));
		}
		return sBuffer.toString();
	}

	private static int bytes2Int(byte[] bytes) {
		// 如果不与0xff进行按位与操作，转换结果将出错，有兴趣的同学可以试一下。
		int int1 = bytes[0] & 0xff;
		int int2 = (bytes[1] & 0xff) << 8;
		int int3 = (bytes[2] & 0xff) << 16;
		int int4 = (bytes[3] & 0xff) << 24;
		return int1 | int2 | int3 | int4;
	}

	private static byte[] getMD5Byte(String str) {
		byte[] md5bt = null;
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md5bt = md.digest(str.getBytes());
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		return md5bt;
	}

}
