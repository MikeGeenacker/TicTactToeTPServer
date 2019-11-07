package com.mike.oop.http.pages;

import com.mike.oop.http.Server;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.Map;

public class EndScreenHandler  implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String, String> params = null;

        try {
            params = Server.queryToMap(exchange.getRequestURI().getQuery());

            if(params.containsKey("winner")) {

            } else {
//                renderDraw();
            }
        }catch (Exception e) {
            System.out.println("alles is kut");
        }
    }

    public String drawPage() {
        return "";
    }
}
