package com.mike.oop.http.game;

public class View {
    int width;
    int height;


    public View(int dimensions) {
        width = dimensions;
        height = dimensions;
    }
    public void draw(Board b, Player[] players, Player currentPlayer) {
        printScore(players);
        printBoard(b);
    }

    private void printBoard(Board b) {
        printBorder();
        for(int[] row : b.getBoard()) {
            printRow(row);
        }
        printBorder();
    }

    private void printBorder() {
        String s = "";
        for(int i =0; i < width+2; i++) {
            s += "=";
        }

        System.out.println(s);
    }

    private void printRow(int[] row) {
        String s = "=";
        for(int cell : row) {
            switch (cell) {
                case 0:
                    s += ".";
                    break;
                case 1:
                    s += "X";
                    break;
                case 2:
                    s += "O";
                    break;
            }
        }
        s += "=";
        System.out.println(s);
    }

    private void printScore(Player[] players) {
        for(Player p : players) {
            System.out.println(p.getName() + ": " + p.getScore().getScore());
        }
    }

    public void drawWinner(Player p) {
        System.out.println(p.getName() + " HEEFT GEWONNEN");
    }



}
