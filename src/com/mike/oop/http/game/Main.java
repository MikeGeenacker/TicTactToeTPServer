package com.mike.oop.http.game;

public class Main {

    static Game g;
    static Human h1;
    static Human h2;
    public static void main(String[] args) {
        h1 = new Human("Mike", Player.Piece.OPIECE);
        h2 = new Human("Syb", Player.Piece.XPIECE);
        try {
            g = new Game(new Player[]{h1, h2});
            g.startGame();
        } catch(Exception e) {

        }
    }

}
