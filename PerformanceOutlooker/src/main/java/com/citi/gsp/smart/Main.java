package com.citi.gsp.smart;

import com.mongodb.client.MongoDatabase;

public class Main {
    public static String ip;
    public static int port;
    public static String userName;
    public static String password;
    public static String fileName;

    private  RequestService requestService;
    private ResponseService responseService;
    private RawService rawService;
    private TrackService trackService;
    public static void main(String[] args) throws Exception{
        PerformanceOutlooker performanceOutlooker = new PerformanceOutlooker(ip, port, userName, password, fileName);
        performanceOutlooker.process();
    }




}
