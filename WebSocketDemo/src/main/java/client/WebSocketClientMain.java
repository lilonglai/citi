package client;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class WebSocketClientMain {
    public static void main(String[] args) throws InterruptedException, URISyntaxException {
        List<WebSocketTestClient> list = new ArrayList<WebSocketTestClient>();
        WebSocketTestClient client = null;
        int count = 10000;
        for (int i = 0; i < count; i++) {
            client = new WebSocketTestClient(i, new URI("ws://localhost:8887"));
            client.connect();
            list.add(client);
        }

        while (true) {
            if (client.isOpen()) {
                //client.send("Hello");
            }
            Thread.sleep(1000);
        }
    }
}
