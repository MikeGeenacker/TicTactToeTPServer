package com.mike.oop.http.pages;

import com.mike.oop.http.Server;
import com.mike.oop.http.game.HtmlView;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class IndexHandler implements HttpHandler {

    private String page(Map<String, String> params) {
        StringBuilder page = new StringBuilder();

        HtmlView view = new HtmlView();

        page.append("<html>");
        page.append("<body>");
        page.append("<p>Hallo Wereld van http</p>");
        page.append(view.renderBoard(null));
        page.append("</body>");
        page.append("</html>");

        return page.toString();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String, String> params = null;

        try {
            params = Server.queryToMap(exchange.getRequestURI().getQuery());
        } catch(Exception e) {
            // niks
        }

        String response = page(params);

        exchange.sendResponseHeaders(200, response.length());

        OutputStream os = exchange.getResponseBody();

        os.write(response.getBytes());
        os.close();
    }
}
