package com.taotao.rest.jedis;

import java.util.HashSet;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	
	@Test
	public void testJedisSingle(){
		Jedis jedis = new Jedis("172.16.16.100", 6379);
		jedis.set("key1", "jedis test");
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close();
	}
	
	/**
	 * Jedis连接池
	 */
	@Test
	public void testJedisPool(){
		JedisPool jedisPool = new JedisPool("172.16.16.100", 6379);
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close(); 
		jedisPool.close();
	}
	
	@Test
	public void testJedisCluster(){
		HashSet<HostAndPort> nodes = new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("172.16.16.100", 7001));
		nodes.add(new HostAndPort("172.16.16.100", 7002));
		nodes.add(new HostAndPort("172.16.16.100", 7003));
		nodes.add(new HostAndPort("172.16.16.100", 7004));
		nodes.add(new HostAndPort("172.16.16.100", 7005));
		nodes.add(new HostAndPort("172.16.16.100", 7006));
		JedisCluster cluster = new JedisCluster(nodes);
		cluster.set("key1", "1000");
		String string = cluster.get("key1");
		System.out.println(string);
		cluster.close(); 
	}
	
	
	@Test
	public void testSpringJedisSingle(){ 
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisPool jedisPool = (JedisPool) context.getBean("redisClient");
		Jedis jedis = jedisPool.getResource();
		String string = jedis.get("key1");
		System.out.println(string);
		jedis.close(); 
		jedisPool.close();
	}
	
	@Test
	public void testSpringJedisCluster(){ 
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
		JedisCluster jedisCluster = (JedisCluster) context.getBean("redisClient");
		String string = jedisCluster.get("key1");
		System.out.println(string);
		jedisCluster.close(); 
	}

}
