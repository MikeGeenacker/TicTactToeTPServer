package com.mike.oop.http.game;

public class HtmlView {
    public String renderPlayer(Player p) {
        StringBuilder html = new StringBuilder();
        html.append("<h1>");
        html.append(p.getName() + " is aan zet");
        html.append("</h1>");

        return html.toString();
    }
    public String renderBoard(Board b) {
        StringBuilder html = new StringBuilder();
        int[][] boardData = b.getBoard();

        html.append("<table>");

        for(int i = 0; i < boardData.length; i++) {
            html.append("<tr>");
            for(int j = 0; j < boardData[i].length; j++) {
                switch (boardData[i][j]) {
                    case 0: // empty
                        html.append(renderEmptyButton(i, j));
                        break;
                    case 1: // xpiece
                        html.append(renderLinkButton(Player.Piece.XPIECE));
                        break;
                    case 2: // xpiece
                        html.append(renderLinkButton(Player.Piece.OPIECE));
                        break;
                    default:
                        html.append(renderLinkButton(null));
                        break;
                }
            }

            html.append("</tr>");
        }
        html.append("</table>");

        return html.toString();
    }

    private String renderEmptyButton(int x, int y) {
        StringBuilder element = new StringBuilder();
        element.append("<td>");
        element.append("<a href=\"/game?move=" + x + "-" + y + "\">");
        element.append("<div style=\"height:100%; width:100%\">");
        element.append(".");
        element.append("</div>");
        element.append("</a>");
        element.append("</td>");

        return element.toString();
    }
    private String renderLinkButton(Player.Piece p) {
        StringBuilder element = new StringBuilder();
        String pieceString = "";

        switch(p) {
            case XPIECE:
                pieceString = "X";
                break;
            case OPIECE:
                pieceString = "O";
                break;
            default:
                pieceString = "??";
                break;
        }

        element.append("<td>");
        element.append("<div style=\"height:100%; width:100%\">");
        element.append(pieceString);
        element.append("</div>");
        element.append("</td>");

        return element.toString();
    }
}
