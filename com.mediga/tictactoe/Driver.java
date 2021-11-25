package com.mediga.tictactoe;

import com.mediga.tictactoe.model.Board;
import com.mediga.tictactoe.model.Move;
import com.mediga.tictactoe.model.Piece;
import com.mediga.tictactoe.model.Player;
import com.mediga.tictactoe.service.Game;

import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Board board = new Board(3);
        String piece = s.next();
        Player player1 = new Player(s.next(), Piece.getPieceFromSymbol(piece), 5);
        piece = s.next();
        Player player2 = new Player(s.next(), Piece.getPieceFromSymbol(piece), 4);
        String next = s.next();
        Game game = new Game(board, List.of(player1, player2));
        while(!next.equalsIgnoreCase("exit")) {
            Move move = new Move(Integer.valueOf(next)-1, s.nextInt()-1);
            game.playNextMove(move);
            if(game.isGameOver()) {
                break;
            }
            next = s.next();
        }
    }
}
