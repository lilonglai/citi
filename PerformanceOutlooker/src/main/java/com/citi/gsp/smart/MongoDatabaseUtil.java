package com.citi.gsp.smart;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

import java.util.ArrayList;
import java.util.List;

public class MongoDatabaseUtil {
    public static String databaseName = "local";
    public static MongoDatabase getDatabase(String ip, int  port, String username, String password) {
        ServerAddress serverAddress = new ServerAddress(ip,port);
        List<ServerAddress> addrs = new ArrayList<ServerAddress>();
        addrs.add(serverAddress);
        MongoCredential credential = MongoCredential.createScramSha1Credential(username, databaseName, password.toCharArray());
        List<MongoCredential> credentials = new ArrayList<MongoCredential>();
        credentials.add(credential);
        MongoClient mongoClient = new MongoClient( addrs, credentials );
        MongoDatabase mongoDatabase = mongoClient.getDatabase(databaseName);
        return mongoDatabase;
    }
}
