package com.mike.oop.http;

import com.mike.oop.http.pages.GameHandler;
import com.mike.oop.http.pages.IndexHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private int portNumber;
    private HttpServer httpServer;

    public Server(int portNumber) {
        this.portNumber = portNumber;
    }

    public void StartServer() throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(portNumber), 0);

        setContextPages();
        httpServer.start();
    }

    private void setContextPages() {
        httpServer.createContext("/", new IndexHandler());
        httpServer.createContext("/game", new GameHandler());
        httpServer.setExecutor(null);
    }
    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<String, String>();
        for(String param: query.split("&")) {
            String pair[] = param.split("=");

            if(pair.length > 1) {
                result.put(pair[0], pair[1]);
            } else {
                result.put(pair[0], "");
            }
        }
        return result;
    }
}
