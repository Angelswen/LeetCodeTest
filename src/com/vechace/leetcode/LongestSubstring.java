package com.vechace.leetcode;

import java.util.HashMap;

public class LongestSubstring {
	
	/**
	 * 题目：求字符串中最长不含重复字符的子串长度
	 * @param s
	 * @return
	 */
	public static int lengthOfLongestSubstring(String s){
		if(s.length()==0){
			return 0;
		}
		
		HashMap<Character,Integer> map = new HashMap<Character,Integer>();
		int max = 0;
		for(int i=0,j=0;i<s.length();++i){
			if(map.containsKey(s.charAt(i))){
				j = Math.max(j, map.get(s.charAt(i))+1);
			}
			map.put(s.charAt(i), i);
			max = Math.max(max, i-j+1);
		}
		return max;
	}

	public static void main(String[] args) {
		
		System.out.println("abcabcbb: "+lengthOfLongestSubstring("abcabcbb"));
		System.out.println("bbbbb: "+lengthOfLongestSubstring("bbbbb"));
		System.out.println("pwwkew: "+lengthOfLongestSubstring("pwwkew"));

	}

}
