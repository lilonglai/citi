package com.citi.gsp.smart;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;

public class TrackService {
    private MongoDatabase mongoDatabase;
    public TrackService(MongoDatabase mongoDatabase) {
        this.mongoDatabase = mongoDatabase;
    }

    public List<Document> listData(String uuid){
        MongoCollection<Document> collection = mongoDatabase.getCollection("Track");
        Bson uuidFilter = Filters.eq("uuid", uuid);
        Bson actionFilter = Filters.eq("action", uuid);
        Bson filters = Filters.and(uuidFilter, actionFilter);
        FindIterable findIterable = collection.find(filters).sort(new BasicDBObject("source",1));
        MongoCursor<Document> mongoCursor = findIterable.iterator();
        List<Document> resultList = new ArrayList<Document>();

        while(mongoCursor.hasNext()){
            resultList.add(mongoCursor.next());
        }
        return resultList;
    }
}
