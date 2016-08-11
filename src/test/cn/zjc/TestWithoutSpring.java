package cn.zjc;

import org.junit.Test;

/**
 * @author zjc
 * @version 2016/8/11 0:51
 * @description
 */
public class TestWithoutSpring {


	@Test
	public void Test1() {
		String[] strs = {"zjc", "zzz", "ssss"};
		byte[][] ss = new byte[strs.length][];
		for (int i = 0; i < strs.length; i++) {
			ss[i] = strs[i].getBytes();
		}
		del(ss);
	}

	private void del(byte[]... key) {
		for (int i = 0; i < key.length; i++) {
			System.out.println("转化的值-->" + new String(key[i]));
		}
	}


	@Test
	public void Test2(){

	}



}
