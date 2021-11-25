package com.mediga.tictactoe.model;

public class Board {
    int size;
    Cell[][] cells;

    public Board(int size ) {
        this.size = size;
        this.cells = new Cell[size][size];
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                this.cells[i][j] = new Cell();
            }
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
}
