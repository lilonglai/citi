package com.citi.gsp.smart;

import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class RequestService {
    private MongoDatabase mongoDatabase;
    public RequestService(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    public List<Document> listData(){
        MongoCollection<Document> collection = mongoDatabase.getCollection("CheckRequest");
        FindIterable findIterable = collection.find();
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List<Document> resultList = new ArrayList<Document>();
        while(mongoCursor.hasNext()){
            resultList.add(mongoCursor.next());
        }
        return resultList;
    }
}
