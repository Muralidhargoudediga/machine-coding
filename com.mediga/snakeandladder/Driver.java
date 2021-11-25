package com.mediga.snakeandladder;

import com.mediga.snakeandladder.model.Board;
import com.mediga.snakeandladder.model.Dice;
import com.mediga.snakeandladder.model.Move;
import com.mediga.snakeandladder.model.Player;
import com.mediga.snakeandladder.service.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Test inpit:
 * 9
 * 62 5
 * 33 6
 * 49 9
 * 88 16
 * 41 20
 * 56 53
 * 98 64
 * 93 73
 * 95 75
 * 8
 * 2 37
 * 27 46
 * 10 32
 * 51 68
 * 61 79
 * 65 84
 * 71 91
 * 81 100
 * 2
 * Murali
 * Sushanth
 */
public class Driver {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int noOfSnakes = s.nextInt();
        Board board = new Board(100);
        for(int i = 0; i < noOfSnakes; i++) {
            int start = s.nextInt();
            int end = s.nextInt();
            Move move = new Move(start, end);
            board.getCells().get(start).setMove(move);
        }

        int noOfLadders = s.nextInt();
        for(int i = 0; i < noOfLadders; i++) {
            int start = s.nextInt();
            int end = s.nextInt();
            Move move = new Move(start, end);
            board.getCells().get(start).setMove(move);
        }

        int noOfPlayers = s.nextInt();
        List<Player> players = new ArrayList<>(noOfPlayers);
        for(int i = 0; i < noOfPlayers; i++) {
            players.add(new Player(s.next()));
        }

        Game game = new Game(new Dice(1), board, players);
        game.playGame();
    }
}
