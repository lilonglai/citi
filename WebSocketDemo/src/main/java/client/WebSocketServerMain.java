package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class WebSocketServerMain {
    public static void main(String[] args) throws InterruptedException, URISyntaxException, UnknownHostException, IOException {
        int port = 8887; // 843 flash policy port

        WebSocketTestServer server = new WebSocketTestServer(port);
        server.start();
        System.out.println("ChatServer started on port: " + server.getPort());

        BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            //String in = sysin.readLine();
            long startTime = System.nanoTime();
            server.broadcast("" +startTime);
            Thread.sleep(1000);
        }
    }
}
