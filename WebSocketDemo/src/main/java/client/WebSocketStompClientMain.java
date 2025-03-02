package client;

import com.google.gson.JsonObject;
import org.springframework.messaging.converter.ByteArrayMessageConverter;
import org.springframework.messaging.converter.GenericMessageConverter;
import org.springframework.messaging.converter.GsonMessageConverter;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class WebSocketStompClientMain {
    public static void main(String[] args) throws InterruptedException, URISyntaxException, ExecutionException {
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(webSocketClient);
        stompClient.setMessageConverter(new GsonMessageConverter());
        //stompClient.setTaskScheduler(taskScheduler); // for heartbeats

        String url = "ws://localhost:8080/gs-guide-websocket";
        int count = 1000;
        for (int i = 0; i < count; i++)
        {
            final int  id = i;
            WebSocketTestStompClient sessionHandler = new WebSocketTestStompClient(id);
            CompletableFuture<StompSession> future = stompClient.connectAsync(url, sessionHandler);
            StompSession stompSession = future.get();
            stompSession.subscribe("/topic/greetings", new StompFrameHandler() {
                @Override
                public Type getPayloadType(StompHeaders headers) {
                    return JsonObject.class;
                }

                @Override
                public void handleFrame(StompHeaders headers, Object payload) {
                    JsonObject jsonObject = (JsonObject) payload;
                    long startTime = jsonObject.get("startTime").getAsLong();
                    long endTime = System.nanoTime();
                    long elapsedTime = endTime - startTime;
                    System.out.println(id + "message:" + payload + ":" + startTime + ":" + endTime + ":" +  elapsedTime);
                }
            });
        }

        stompClient.start();

        while (true) {
            if (stompClient.isRunning()) {
                //client.send("Hello");
            }
            Thread.sleep(1000);
        }
    }
}
