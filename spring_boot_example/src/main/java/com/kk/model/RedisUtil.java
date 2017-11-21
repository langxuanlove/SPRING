package com.gnet.model;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

public class RedisUtil {
	private Logger logger=Logger.getLogger(RedisUtil.class);
	private static RedisUtil instance = null;
	private JedisCluster cluster;
	private RedisUtil() {
		// do something
		Set<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.16.121", 7000));
		nodes.add(new HostAndPort("192.168.16.121", 7001));
		nodes.add(new HostAndPort("192.168.16.122", 7000));
		nodes.add(new HostAndPort("192.168.16.122", 7001));
		nodes.add(new HostAndPort("192.168.16.124", 7000));
		nodes.add(new HostAndPort("192.168.16.124", 7001));
		JedisPoolConfig config = new JedisPoolConfig();
		config.setMaxWaitMillis(60000);
		config.setMaxTotal(100);
		config.setMaxIdle(10);
		cluster = new JedisCluster(nodes, 60000, 10, config);
	}
	public static RedisUtil getInstance() {
		if (instance == null) {
			synchronized (RedisUtil.class) {
				if (null == instance) {
					instance = new RedisUtil();
				}
			}
		}
		return instance;
	}
	public String getRedisMsg(String key){
		logger.info("请求RedisUtil工具类.");
		return cluster.get(key);
	}
	
	public String setKeyValue(String key,String value){
			return  cluster.set(key, value);
	}
	
	public JedisCluster getCluster() {
		return cluster;
	}
	public void setCluster(JedisCluster cluster) {
		this.cluster = cluster;
	}
}