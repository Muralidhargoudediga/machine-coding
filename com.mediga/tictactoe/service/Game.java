package com.mediga.tictactoe.service;

import com.mediga.tictactoe.model.*;

import java.util.List;

public class Game {
    Board board;
    List<Player> players;
    int noOfPlayers;
    int currentPlayer;
    boolean isGameOver;
    int fillCount;

    public Game(Board board, List<Player> players) {
        this.board = board;
        this.players = players;
        this.noOfPlayers = players.size();
        this.currentPlayer = 0;
    }

    public void playNextMove(Move move) {
        Cell cell = board.getCells()[move.getRow()][move.getColumn()];
        if(isValidMove(cell)) {
            fillCount++;
            Player player = players.get(currentPlayer);
            cell.setPiece(player.getPiece());
            if(isPlayerWon(move, player)) {
                System.out.println(player.getName() + " has won");
                isGameOver = true;
                return;
            }
            if(fillCount == board.getSize() * board.getSize()) {
                isGameOver = true;
                System.out.println("Game Over");
                return;
            }
            printBoard();
            currentPlayer = (currentPlayer+1)%noOfPlayers;
        } else {
            System.out.println("Invalid Move");
        }
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    private boolean isValidMove(Cell cell) {
        if(cell.getPiece() != Piece.EMPTY) {
            return false;
        }
        return true;
    }

    private boolean isPlayerWon(Move move, Player player) {
        int rows = 0, cols = 0, diagnal1 = 0, diagnal2 = 0;
        for(int i = move.getRow(), j = 0; j < board.getSize(); j++) {
            if(player.getPiece() == board.getCells()[i][j].getPiece()) {
                rows++;
            }
        }
        for(int i = 0, j = move.getColumn(); i < board.getSize(); i++) {
            if(player.getPiece() == board.getCells()[i][j].getPiece()) {
                cols++;
            }
        }

        if(move.getRow() == move.getColumn()) {
            for(int i = 0; i < board.getSize(); i++) {
                if(player.getPiece() == board.getCells()[i][i].getPiece()) {
                    diagnal1++;
                }
            }
        }

        if((move.getRow() + move.getColumn()) == board.getSize()-1) {
            for(int i = 0, j = board.getSize()-1; i < board.getSize() && j >= 0; i++, j--) {
                if(player.getPiece() == board.getCells()[i][j].getPiece()) {
                    diagnal2++;
                }
            }
        }
        int size = board.getSize();
        return (rows == size) || (cols == size) || (diagnal1 == size) || (diagnal2 == size);
    }

    private void printBoard() {
        for(int i = 0; i < board.getSize(); i++) {
            for(int j = 0; j < board.getSize(); j++) {
                System.out.print(board.getCells()[i][j].getPiece().getSymbol());
            }
            System.out.println();
        }
    }
}
