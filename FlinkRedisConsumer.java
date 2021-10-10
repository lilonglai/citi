package flink;

import org.apache.commons.lang3.StringUtils;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.source.RichParallelSourceFunction;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.StreamEntry;
import redis.clients.jedis.StreamEntryID;
import redis.clients.jedis.params.XReadGroupParams;

import java.util.*;

public class FlinkRedisConsumer <T> extends RichParallelSourceFunction<T> {
    private DeserializationSchema<T> valueDeserializer;
    private Properties props;
    private transient volatile boolean isRunning;
    private transient JedisCluster jedisCluster;

    private String group;
    private String consumer;

    XReadGroupParams xReadGroupParams;
    Map<String, StreamEntryID> topicMap;
    public static String CONST_SERVERS = "servers";
    public static String CONST_TOPIC = "topic";
    public static String CONST_GROUP = "group";
    public static String CONST_CONSUMER = "consumer";
    public static String CONST_COUNT = "count";
    public static String CONST_BLOCK = "block";


    public FlinkRedisConsumer(DeserializationSchema<T> valueDeserializer, Properties props) {
        super();
        this.valueDeserializer = valueDeserializer;
        this.props = props;
    }

    @Override
    public void run(SourceContext<T> ctx) throws Exception {
        while (isRunning) {
            // this synchronized block ensures that state checkpointing,
            // internal state updates and emission of elements are an atomic operation

            List<Map.Entry<String, List<StreamEntry>>> entryList = jedisCluster.xreadGroup(group,consumer, xReadGroupParams, topicMap);
            for(Map.Entry<String, List<StreamEntry>> entry: entryList) {
                String key = entry.getKey();;
                List<StreamEntry> streamEntryList = entry.getValue();
                for(StreamEntry streamEntry: streamEntryList) {
                    StreamEntryID streamEntryID = streamEntry.getID();
                    Map<String, String> fields = streamEntry.getFields();
                    String content = fields.get("content");
                    T element = valueDeserializer.deserialize(content.getBytes());
                    synchronized (ctx.getCheckpointLock()) {
                        ctx.collect(element);
                    }
                }
            }

        }
    }

    @Override
    public void cancel() {
        isRunning = false;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        super.open(parameters);
        Set<HostAndPort> jedisClusterNodes = new HashSet<HostAndPort>();
        String serversList = props.getProperty(CONST_SERVERS);
        if(StringUtils.isNotEmpty(serversList)) {
            String[] serversArray = serversList.split(";");
            for (String server: serversArray) {
                String[] hostAndPoort = server.split(":");
                jedisClusterNodes.add(new HostAndPort(hostAndPoort[0], Integer.valueOf(hostAndPoort[1])));
            }
        }
        jedisCluster = new JedisCluster(jedisClusterNodes);

        group = props.getProperty(CONST_GROUP);
        consumer = props.getProperty(CONST_CONSUMER);
        int count = Integer.valueOf(props.getProperty(CONST_COUNT, "1"));

        int block = Integer.valueOf(props.getProperty(CONST_BLOCK, "0"));

        xReadGroupParams =XReadGroupParams.xReadGroupParams().count(count).block(0);

        String topic = props.getProperty(CONST_TOPIC);

        topicMap.put(topic, StreamEntryID.LAST_ENTRY);
    }

    @Override
    public void close() throws Exception {
        super.close();
    }
}
