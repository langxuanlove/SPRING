package com.gnet.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;


public class SessionMapUtil {
	public static BiMap<String,Object> map = HashBiMap.create();
//	public static  Map<String, Object> map=new ConcurrentHashMap<String, Object>();
	public static  List<Object> list=new ArrayList<Object>();
	public static Object get(String id){
		return map.get(id);
	}; 
	public static void put(String id,Object object) {
		map.put(id, object);
		list.add(object);
	}
	public static List<Object> listObject(){
		return list;
	}
	public static void remove(Object obj){
		map.remove(obj);
		list.remove(obj);
	}
}
