package com.vechace.leetcode;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 解法一：利用HashMap + 双向链表实现LRUCache算法
 * @author vechace
 *
 */
public class LRUCache {
	
	//定义内部节点类，实现双向链表
	private class Node{
		
		int key ,value;
		Node prew,next;
		Node(int k,int v){
			this.key = k;
			this.value = v;
		}
		Node(){
			this(0,0);
		}
	}
	
	private int capacity,count;
	private Map<Integer,Node>map;
	private Node head,tail;
	
	//构造函数
	public LRUCache(int capacity){
		this.capacity = capacity;
		this.count = 0;
		map = new HashMap<>();
		head = new Node();
		tail = new Node();
		head.next = tail;
		tail.prew = head;
		
	}
	
	public int get(int key){
		Node n = map.get(key);
		if(n == null){
			return -1;
		}
		
		update(n);
		return n.value;
	}
	
	public void set(int key,int value){
		Node n = map.get(key);
		if(null == n){
			n = new Node(key,value);
			map.put(key, n);
			add(n);
			++count;
		}else{
			n.value = value;
			update(n);
		}
		if(count>capacity){
			Node toDel = tail.prew;
			remove(toDel);
			map.remove(toDel.key);
			--count;
		}
		
	}
	
	private void update(Node node){
		remove(node);
		add(node);
		
	}
	
	private void add(Node node){
		Node after = head.next;
		head.next = node;
		node.prew = head;
		node.next = after;
		after.prew = node;
	}
	
	private void remove(Node node){
		Node before = node.prew;
		Node after = node.next;
		before.next = after;
		after.prew = before;
	}
	

	public static void main(String[] args) {

	}

}



/**
 * 解法二：利用LinkedHashMap实现LRUCache算法
 * @author vechace
 *
 */
class LRUCache_v2{
	
	private LinkedHashMap<Integer, Integer> map;
	private final int CAPACITY;
	
	public LRUCache_v2(int capacity){
		CAPACITY = capacity;
		map = new LinkedHashMap<Integer,Integer>(capacity,0.75f,true){
			protected boolean removeEldestEntry(Map.Entry eldest) {
				return size() > CAPACITY;
			}
		};
		
	}
	
	public int get(int key){
		return map.getOrDefault(key, -1);
	}
	
	public void set(int key,int value){
		map.put(key, value);
	}
	
}
