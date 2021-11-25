package com.mediga.snakeandladder.model;

import java.util.ArrayList;
import java.util.List;

public class Board {
    int size;
    List<Cell> cells;

    public Board(int size) {
        this.size = size;
        this.cells = new ArrayList<>(size+1);
        for(int i = 0; i <= size; i++ ) {
            cells.add(new Cell());
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }
}
