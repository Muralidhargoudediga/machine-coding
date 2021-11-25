package com.mediga.snakeandladder.service;

import com.mediga.snakeandladder.model.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Game {
    Dice dice;
    Board board;
    Queue<Player> queue;
    Random random = new Random();

    public Game(Dice dice, Board board, List<Player> players) {
        this.dice = dice;
        this.board = board;
        this.queue = new LinkedList<>(players);
    }

    public void playGame() {
        while(queue.size() > 1) {
            Player player = queue.poll();
            int diceRoll = rollDice();
            int nextPosition = diceRoll + player.getPosition();
            if(nextPosition > board.getSize()) {
                queue.offer(player);
                continue;
            }
            if(nextPosition == board.getSize()) {
                System.out.println(player.getName() + " has won");
                continue;
            }
            Move move = board.getCells().get(nextPosition).getMove();
            System.out.println(player.getName() + " rolled " + diceRoll);
            if(move == null) {
                System.out.println("Moved from position "+ player.getPosition() + " to position " + nextPosition);
                player.setPosition(nextPosition);
                queue.offer(player);
            } else {
                while(move != null) {
                    nextPosition = move.getEnd();
                    if(move.getStart() > move.getEnd()) {
                        System.out.println("and swallowed by snake. Moved from position "+ player.getPosition() + " to position " + nextPosition);
                    } else {
                        System.out.println("Climbed the ladder. Moved from position "+ player.getPosition() + " to position " + nextPosition);
                    }
                    player.setPosition(nextPosition);
                    move = board.getCells().get(nextPosition).getMove();
                }
                if(nextPosition == board.getSize()) {
                    System.out.println(player.getName() + " has won");
                } else {
                    queue.add(player);
                }
            }
        }
    }

    private int rollDice() {
        return random.nextInt(6*dice.getNoOfDice() - dice.getNoOfDice() ) + dice.getNoOfDice() + 1;
    }
}
