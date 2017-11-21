package com.gnet.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;


public class SessionMapUtil {
	private static Logger logger=LoggerFactory.getLogger(SessionMapUtil.class);
	public static BiMap<String,Object> map = HashBiMap.create();
//	public static  Map<String, Object> map=new ConcurrentHashMap<String, Object>();
	public static  List<Object> list=new ArrayList<Object>();
	public static Object get(String id){
		return map.get(id);
	}; 
	public static void put(String id,Object object) {
		map.put(id, object);
		if(!list.contains(object))list.add(object);
	}
	public static List<Object> listObject(){
		return list;
	}
	public static void remove(Object obj){
		map.inverse().remove(obj);
		list.remove(obj);
		logger.info("map中的值:{}",map);
	}
}
