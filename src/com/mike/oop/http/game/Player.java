package com.mike.oop.http.game;

import java.util.Scanner;

public abstract class Player {
    private String name;
    private Piece piece;
    private Score score;

    public Player(String name, Piece p) {
        this.name = name;
        piece = p;
        score = new Score();
    }

    public int[] doMove() {
        return new int[]{0, 0};
    }

    public int[] httpDoMove(String col, String row)throws InvalidMoveException { return new int[] {0, 0};}

    public String getName() {
        return name;
    }

    public Score getScore() {
        return score;
    }

    public boolean exitGame() {
        return false;
    }

    public Piece getPiece() {
        return piece;
    }


    public enum Piece {
        EMPTY, OPIECE,XPIECE;

    }



    public class Score {
        private int currentScore;

        public Score() {
            currentScore = 0; // niet nodig maar het is goed om expliciet te zijn
        }
        public void countScore() {
            currentScore++;
        }

        public int getScore() {
            return currentScore;
        }
    }
}

