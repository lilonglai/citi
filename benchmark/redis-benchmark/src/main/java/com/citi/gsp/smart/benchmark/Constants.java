package com.citi.gsp.smart.benchmark;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Constants {
    public static final int warmupCount= 2;
    public static final int lookCount = 1;
    public static final int threadCount = 32;

    public static final int iterationTime = 10;

    public static final String redisURL = "192.168.11.128:9001;192.168.11.128:9002";

    public static  List<HostPort> getRedisServerList(){
        String[] urls = redisURL.split(";");
        List<HostPort> list = new ArrayList<HostPort>();
        for (String url: urls){
            String[] array = url.split(":");
            HostPort hostPort = new HostPort(array[0], Integer.valueOf(array[1]));
            list.add(hostPort);
        }
        return list;
    }

    private static ThreadLocalRandom random =  ThreadLocalRandom.current();

    public static int generateKey(){
        return random.nextInt(10000);
    }
}
