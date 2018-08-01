package com.vechace.leetcode;

/**
 * 通配符匹配:'？' 匹配任何单个字符;'*'匹配任何字符序列（包括空序列）;
 * 输入：字符串（s）：可能是空的，只包含小写字母a-z; 模式（p）:可能是空的，只包含小写字母a-z和字符，如? 或  *;
 * @author vechace
 *
 */
public class WildcardMatching {
	
	public static boolean isMatching(String s,String p){
		
		boolean[][] match = new boolean[s.length()+1][p.length()+1];
		match[s.length()][p.length()] = true;
		//先检查模式p是否具有通配符：'*'
		for(int i = p.length()-1;i>=0;i--){
			if(p.charAt(i)!='*'){
				break;
			}else{
				match[s.length()][i] = true;
			}
		}
		
		/*模式匹配：可以模拟棋盘来实现,以（abc,ab?）为例，如下：*/
		/*
		  | a  b  ?
		--|-----------------
		a | 1  0  0  0
		b | 0  1  0  0
		c | 0  0  1  0
		  | 0  0  0  1
		  |
		*/
		for(int i = s.length()-1;i>=0;i--){
			for(int j = p.length()-1;j>=0;j--){
				if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
					match[i][j] = match[i+1][j+1];
				}else if(p.charAt(j) == '*'){
					match[i][j] = match[i+1][j] || match[i][j+1];
				}else{
					match[i][j] = false;
				}
			}
		}
		
		return match[0][0];
	}

	public static void main(String[] args) {
		
		System.out.println("测试1： （a,aa）"+isMatching("a", "aa"));
		System.out.println("测试2： （a,*a）"+isMatching("a", "*a"));
		System.out.println("测试3： （ac,a?）"+isMatching("ac", "a?"));
		System.out.println("测试4： （abcde,*a*e）"+isMatching("abcde", "*a*e"));


	}

}
