package com.mediga.snakeandladder.model;

public class Cell {
    Move move;

    public Cell() {
    }

    public Cell(Move move) {
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }
}
