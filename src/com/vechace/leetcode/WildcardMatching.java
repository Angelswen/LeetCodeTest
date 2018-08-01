package com.vechace.leetcode;

/**
 * ͨ���ƥ��:'��' ƥ���κε����ַ�;'*'ƥ���κ��ַ����У����������У�;
 * ���룺�ַ�����s���������ǿյģ�ֻ����Сд��ĸa-z; ģʽ��p��:�����ǿյģ�ֻ����Сд��ĸa-z���ַ�����? ��  *;
 * @author vechace
 *
 */
public class WildcardMatching {
	
	public static boolean isMatching(String s,String p){
		
		boolean[][] match = new boolean[s.length()+1][p.length()+1];
		match[s.length()][p.length()] = true;
		//�ȼ��ģʽp�Ƿ����ͨ�����'*'
		for(int i = p.length()-1;i>=0;i--){
			if(p.charAt(i)!='*'){
				break;
			}else{
				match[s.length()][i] = true;
			}
		}
		
		/*ģʽƥ�䣺����ģ��������ʵ��,�ԣ�abc,ab?��Ϊ�������£�*/
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
		
		System.out.println("����1�� ��a,aa��"+isMatching("a", "aa"));
		System.out.println("����2�� ��a,*a��"+isMatching("a", "*a"));
		System.out.println("����3�� ��ac,a?��"+isMatching("ac", "a?"));
		System.out.println("����4�� ��abcde,*a*e��"+isMatching("abcde", "*a*e"));


	}

}
