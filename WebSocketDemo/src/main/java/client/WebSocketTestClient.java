package client;

import java.net.URI;
import java.net.URISyntaxException;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

public class WebSocketTestClient extends WebSocketClient {
    int id;
    public WebSocketTestClient(int id,URI serverURI) {
        super(serverURI);
        this.id = id;
    }

    @Override
    public void onOpen(ServerHandshake serverHandshake) {
    }

    @Override
    public void onMessage(String message) {
        try {
            long startTime = Long.valueOf(message.toString());
            long endTime = System.nanoTime();
            System.out.println(id + ":" + startTime + ":" + endTime + ":" + (endTime - startTime));
        }catch (Exception e) {

        }
    }

    @Override
    public void onClose(int i, String s, boolean b) {
    }

    @Override
    public void onError(Exception e) {
    }
}
