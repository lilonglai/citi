import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisAdvancedClusterCommands;

import java.util.Arrays;

public class Lettuce {
    public static void main(String[] args) {
        RedisURI node1 = RedisURI.create("192.168.11.128", 9001);

        RedisClusterClient clusterClient = RedisClusterClient.create(Arrays.asList(node1));
        StatefulRedisClusterConnection<String, String> connection = clusterClient.connect();
        RedisAdvancedClusterCommands<String, String> syncCommands = connection.sync();
        syncCommands.set("foo", "bar");
        String value = syncCommands.get("foo");
        System.out.println(value);
        connection.close();
        clusterClient.shutdown();
    }
}
