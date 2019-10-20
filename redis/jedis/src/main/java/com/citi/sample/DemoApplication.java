package com.citi.sample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class DemoApplication   implements CommandLineRunner{
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	public void run(String... args) throws Exception {
		String redisHost = "172.16.28.10";
		int numbers = 10000;
		if(args.length >= 1){
			redisHost = args[0];
		}

		if(args.length >=2){
			numbers = Integer.valueOf(args[1]);
		}

		Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
		jedisClusterNodes.add(new HostAndPort(redisHost, 6379));
		JedisCluster jc = new JedisCluster(jedisClusterNodes);

		long startTime = System.currentTimeMillis();
		for(int i =0; i<numbers; i++){
			String key = "key_" + i;
			String value = "value1";
			jc.set(key, value);
		}

		long endTime = System.currentTimeMillis();
		System.out.println("eclaspsed set time:" + ((endTime - startTime)/1000));

		startTime = System.currentTimeMillis();
		for(int i =0; i<numbers; i++){
			String key = "key_" + i;
			String value =jc.get(key);
		}

		endTime = System.currentTimeMillis();
		System.out.println("eclaspsed get time:" + ((endTime - startTime)/1000));

	}
}
