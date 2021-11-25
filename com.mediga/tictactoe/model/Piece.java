package com.mediga.tictactoe.model;

import java.util.Arrays;

public enum Piece {
    XPIECE("X"), OPIECE("O"), EMPTY("-");

    private String symbol;
    Piece(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public static Piece getPieceFromSymbol(String symbol) {
        return Arrays.stream(Piece.values()).filter(p -> p.symbol.equals(symbol)).findFirst().get();
    }
}
