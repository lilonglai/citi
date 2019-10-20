package sample;

import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner{
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

		RedisClusterClient redisClient = RedisClusterClient.create("redis://" + redisHost + ":6379");

		StatefulRedisClusterConnection<String, String> connection = redisClient.connect();

		RedisAdvancedClusterCommands<String, String> commands = connection.sync();

		long startTime = System.currentTimeMillis();
		for(int i =0; i<numbers; i++){
			String key = "key_" + i;
			String value = "value1";
			commands.set(key, value);
		}

		long endTime = System.currentTimeMillis();
		System.out.println("eclaspsed set time:" + ((endTime - startTime)/1000));

		startTime = System.currentTimeMillis();
		for(int i =0; i<numbers; i++){
			String key = "key_" + i;
			String value =commands.get(key);
		}

		endTime = System.currentTimeMillis();
		System.out.println("eclaspsed get time:" + ((endTime - startTime)/1000));

		connection.close();
		redisClient.shutdown();
	}
}
