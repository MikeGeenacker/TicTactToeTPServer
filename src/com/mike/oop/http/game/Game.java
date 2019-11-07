package com.mike.oop.http.game;


import java.util.Random;
import java.util.Scanner;

public class Game {
    private Player[] players;
    private Player currentPlayer;
    private Board board;
    private boolean gameRunning;
    View view;

    public Game(Player[] players) throws Exception {
        if (players.length > 2)
            throw new Exception("niet meer dan 2 spelers kunnen tegelijk spelen");

        if (players[0].getPiece() == players[1].getPiece())
            throw new Exception("Spelers mogen niet dezelfde Piece enums hebben");

        board = new Board(3);
        view = new View(3);
        this.players = players;

        Random r = new Random();
        currentPlayer = players[r.nextInt(1)];
    }

    public void startGame() {
        gameRunning = true;
        runningGame();
    }

    public void startGameHttp() {
        gameRunning= true;
    }

    public void runningHttpGame(String col, String row) throws InvalidMoveException {
        if(gameRunning) {
            playerTurnHttp(col, row);

            if (checkWinner(currentPlayer)) {
                currentPlayer.getScore().countScore();

                System.out.println(currentPlayer + " wint");
                gameRunning = false;
//                newGame();
            } else if (bordVol(board)) {
                System.out.println("Niemand heeft gewonnen");
                gameRunning = false;
//                newGame(); // todo, nieuwe game
            }
        }
    }
    public Board getBoard() {
        return board;
    }
    private void runningGame() {
        while (gameRunning) {
            view.draw(board, players, currentPlayer);
            System.out.println(currentPlayer.getName() + " is aan zet");

            playerTurn();

            if (checkWinner(currentPlayer)) {
                view.drawWinner(currentPlayer);
                currentPlayer.getScore().countScore();

                gameRunning = false;
                newGame();
            } else if (bordVol(board)) {
                System.out.println("Niemand heeft gewonnen");
                gameRunning = false;
                newGame();
            }

            swapPlayers();
        }
    }

    private void newGame() {
        board.clear();
        Scanner scan = new Scanner(System.in);
        System.out.println("New Game? (Y/N)");
        String input = scan.nextLine();

        boolean inCorrectInput = !(input.equals("Y") || input.equals("N"));

        while (inCorrectInput) {
            System.out.println("Foute input, probeer opnieuw");
            input = scan.nextLine();
            inCorrectInput = !(input.equals("Y") || input.equals("N"));
        }

        if (input.equals("Y")) {
            startGame();
        } else {
            System.out.println("exiting...");
        }
    }

    public boolean getGameState() {
        return gameRunning;
    }

    //JAVA is stom
    public void swapPlayers() {
        if (currentPlayer == players[0]) {
            currentPlayer = players[1];
        } else {
            currentPlayer = players[0];
        }
    }

    private void playerTurn() {
        int[] coords;
        boolean hasPlayerMoved = false;

        while (!hasPlayerMoved) {
            coords = currentPlayer.doMove();
            if (board.isValidMove(coords[1], coords[0])) {
                try {
                    board.updateBoard(coords[1], coords[0], currentPlayer.getPiece());
                    hasPlayerMoved = true;
                } catch (BoardInputException ex) {
                    System.out.println(ex);
                    hasPlayerMoved = false;
                }

            } else {
                System.out.println("geen valid move");
            }
        }
    }

    public void playerTurnHttp(String col, String row) throws InvalidMoveException {
        int[] coords = currentPlayer.httpDoMove(col, row);

        if (board.isValidMove(coords[0], coords[1])) {
            try {
                board.updateBoard(coords[0], coords[1], currentPlayer.getPiece());
            } catch (BoardInputException ex) {
                System.out.println(ex);
            }
        } else {
            System.out.println("geen valid move");
        }
    }

    // public omdat java kut is
    public boolean bordVol(Board b) {
        int[][] bData = b.getBoard();

        for (int[] row : bData) {
            for (int cel : row) {
                if (cel == 0)
                    return false;
            }
        }
        return true;
    }

    public boolean checkWinner(Player p) {
        int[][] bData = board.getBoard();
        Player.Piece piece = p.getPiece();
        boolean horRijEen = checkPiece(piece, bData[0][0], bData[0][1], bData[0][2]);
        boolean horRijTwee = checkPiece(piece, bData[1][0], bData[1][1], bData[1][2]);
        boolean horRijDrie = checkPiece(piece, bData[2][0], bData[2][1], bData[2][2]);

        boolean verRijEen = checkPiece(piece, bData[0][0], bData[1][0], bData[2][0]);
        boolean verRijTwee = checkPiece(piece, bData[0][1], bData[1][1], bData[2][1]);
        boolean verRijDrie = checkPiece(piece, bData[0][2], bData[1][2], bData[2][2]);

        boolean diagEen = checkPiece(piece, bData[0][0], bData[1][1], bData[2][2]);
        boolean diagTwee = checkPiece(piece, bData[2][0], bData[1][1], bData[0][2]);
        if (horRijEen || horRijTwee || horRijDrie || verRijEen || verRijTwee || verRijDrie ||
                diagEen || diagTwee) {
            return true;
        } else {
            return false;
        }
    }


    // omdat java kut is public
    public boolean checkPiece(Player.Piece p, int... reeks) {
        int val = 99;
        switch (p) {
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

        if (reeks.length != 3)
            return false;

        if (reeks[0] == val && reeks[1] == val && reeks[2] == val)
            return true;

        return false;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
