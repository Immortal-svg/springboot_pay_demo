package com.example.demo.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class CreateCDKUtils {
	
	private static final int mask = 23;//大数异或的标
	private static Map<Integer, Integer> base = new HashMap<Integer, Integer>();//参数
	private static Random random = new Random();//随机数
	private static final int MAX_NUM = 10;//生成个数
	private static Set<String> cdks = new HashSet<>();//用于存储cdks

	/**
	 * 生成cdks
	 * base数组数据采用每次随机的方式,仅为了在每一组所产生的的cdk的后缀不相同
	 *
	 */
	private static void generateCdks() {
	    List<Integer> indexs = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 16, 17, 18, 19));
	    for (int i = 0; i < indexs.size(); i++) {
	        int key = indexs.get(i);
	        int random = random(312356055, 2094271335);//给出随机数的范围 这个随便
	        base.put(key, random);
	    }

	    for (int i = 0; i < MAX_NUM; i++) {
	        String string = Long.toString(arithmetics(i), 36);// 10进制转36进制,缩短位数(6位36进制数字即可表示:0-2176782335)
	        if (string.length() == 6) {
	        	 string=string.substring(0,string.length()-1);
	             cdks.add(string);//拼接结果
	        }
	    }
	}

	/**
	 * Arithmetics
	 * @param n
	 * @return
	 */
	private static int arithmetics(int n) {
	    int idx = n & mask;
	    int xor = base.get(idx) ^ n;
	    return ((xor | mask) ^ mask) | idx;
	}

	/**
	 * 获取一个cdk
	 * @return
	 */
	private static String takeOneCdk() {
	    String str = "";
	    Iterator<String> iterator = cdks.iterator();
	    while (iterator.hasNext()) {
	        str = iterator.next();
	        break;
	    }
	    if(str == null){
	        return null;
	    }
	    cdks.remove(str);
	    long cdkLength = cdks.size();
	    if (cdkLength == 0) {	
	        generateCdks();	      
	    }
	    return str;
	}


	private static int random(int i, int j) {
	    return random.nextInt(j + 1 - i) + i;
	}
	
	/**
	 * 获取一组CDK
	 * @return
	 */
	public static String getCDK(){
		//生成cdk
		generateCdks();
		StringBuffer CDK=new StringBuffer();
		 //获取cdk   
	    	for(int j=0;j<3;j++){
	    		CDK.append(takeOneCdk()+"-");
	    		if(j==2){
	    			CDK.append(takeOneCdk());
	    		}
	    	}	   
	 return CDK.toString().toUpperCase();
	}
	

}

