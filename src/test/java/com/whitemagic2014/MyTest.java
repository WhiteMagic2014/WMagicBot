package com.whitemagic2014;
import org.junit.Test;

import com.whitemagic2014.pojo.Version;

public class MyTest {

	@Test
	public void test1() {
		Version v1 = new Version("2.0.2");
		System.out.println(v1.getMain()+","+v1.getChild());
		
		Version v2 = new Version("2.0.2");
		System.out.println(v2.getMain()+","+v2.getChild());
		

		if (v2.compareTo(v1) < 0) {
			System.out.println("小于");
		}else if (v2.compareTo(v1) > 0) {
			System.out.println("大于");
		}else {
			System.out.println("等于");
		}
	}

	
	@Test
	public void test2() {
		
	
	}
	
}
