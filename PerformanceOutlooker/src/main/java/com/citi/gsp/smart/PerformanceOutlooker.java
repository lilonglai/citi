package com.citi.gsp.smart;

import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

public class PerformanceOutlooker {
    private  RequestService requestService;
    private ResponseService responseService;
    private RawService rawService;
    private TrackService trackService;

    FileOutputStream outputStream;

    public PerformanceOutlooker(String ip, int  port, String username, String password, String outputFileName) throws Exception{
        MongoDatabase mongoDatabase = MongoDatabaseUtil.getDatabase(ip, port, username, password);
        requestService = new RequestService(mongoDatabase);
        responseService = new ResponseService(mongoDatabase);
        rawService = new RawService(mongoDatabase);
        trackService = new TrackService(mongoDatabase);

        outputStream = new FileOutputStream(outputFileName);
    }

    void process() throws Exception{
        List<Document> requestList = requestService.listData();
        for(Document request: requestList){
            String uuid = request.getString("uuid");
            List<Document> responseList = responseService.listData(uuid);
            List<Document> rawList = rawService.listData(uuid);
            List<Document> trackList = trackService.listData(uuid);
            String singleLine = processSingle(request, responseList, rawList, trackList);
            outputStream.write(singleLine.getBytes());
        }
        outputStream.close();
    }

    String  processSingle(Document request, List<Document> responseList, List<Document> rawList, List<Document> trackList) {
        Date sentRequestTime = request.getDate("generatedTime");
        Date startTime = null;
        Date endTime = null;
        String responseString1 = "";
        String responseString2 = "";
        for(Document raw: rawList){
            String payloadType = raw.getString("payloadType").trim();
            if(payloadType.equals("REQUEST")){
                startTime = raw.getDate("generatedTime");
            }
            else if(payloadType.equals("RESPONSE")){
                endTime = raw.getDate("generatedTime");
            }
            else {
                String source = raw.getString("source");
                if (source.endsWith("-1")) {
                    responseString1 = processSingleResponse(raw, responseList.get(0), trackList.get(0));
                }
                else{
                    responseString2 =  processSingleResponse(raw, responseList.get(1), trackList.get(1));
                }
            }
        }

        String line = startTime + "," + endTime + "," + sentRequestTime + responseString1 + "," + responseString2 + "\n";
        return line;
    }

    String processSingleResponse(Document raw, Document response, Document track) {
        String resultString = "";
        Date receiveRequestTime = raw.getDate("generatedTime");
        Date sentResponseTime = response.getDate("generatedTime");
        Object timelines = track.get("timelines");

    }
}
