package com.mike.oop.http.game;

import java.util.Arrays;

public class Board {
    private int[][] board;

    /**
     * maakt een Board class aan met dimensions*dimensions dimensies
     *
     * @param dimensions de hoogte en breedte van het Board
     */
    public Board(int dimensions) {
        board = new int[dimensions][dimensions];
        clear();
    }

    public boolean isValidMove(int x, int y) {
        if (x > board[0].length-1 || y > board.length-1)
            return false;

        return board[y][x] == 0;
    }

    public void updateBoard(int x, int y, Player.Piece p) throws BoardInputException{
        int val=0 ;
        switch(p) {
            case EMPTY:
                val = 0;
                break;
            case XPIECE:
                val = 1;
                break;
            case OPIECE:
                val = 2;
                break;
        }
        if (y >= board.length || x >= board[0].length){
            throw new BoardInputException("Niet geldig");
        }
        board[y][x] = val;
    }

    public void clear() {
        // vul het hele bord met leeg
        for (int[] row : board) {
            Arrays.fill(row, 0);
        }
    }
    public int[][] getBoard() {
        return board;
    }


}
