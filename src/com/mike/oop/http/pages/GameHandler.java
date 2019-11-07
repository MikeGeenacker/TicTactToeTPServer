package com.mike.oop.http.pages;

import com.mike.oop.http.Server;
import com.mike.oop.http.game.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class GameHandler implements HttpHandler {
    Game game;

    private String page(Game game) {
        StringBuilder page = new StringBuilder();

        HtmlView view = new HtmlView();

        page.append("<html>");
        page.append("<body>");
        page.append(view.renderBoard(game.getBoard()));
        page.append("</body>");
        page.append("</html>");

        return page.toString();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Map<String, String> params = null;

        try {
            params = Server.queryToMap(exchange.getRequestURI().getQuery());
            if (params.containsKey("newGame")) {

                game = initGame();
                game.startGameHttp();
            } else if (params.containsKey("move")) {

                String row = params.get("move").substring(0, 1);
                String col = params.get("move").substring(2, 3);
                try {
                    game.runningHttpGame(col, row);
                } catch (InvalidMoveException ex) {
                   // todo
                }

                if(game.checkWinner(game.getCurrentPlayer())) {
                    // draw winstscherm
                } else if (game.bordVol(game.getBoard())) {
                    // draw draw(lol) scherm
                }

                game.swapPlayers();
            }
        } catch (Exception e) {
            // niks
        }

        String response = page(game);

        exchange.sendResponseHeaders(200, response.length());

        OutputStream os = exchange.getResponseBody();

        os.write(response.getBytes());
        os.close();
    }

    private Game initGame() {
        Game g = null;
        Player p1 = new Human("Mike", Player.Piece.XPIECE);
        Player p2 = new Human("Ekim", Player.Piece.OPIECE);

        try {
            g = new Game(new Player[]{p1, p2});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return g;
    }
}
