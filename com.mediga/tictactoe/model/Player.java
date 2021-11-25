package com.mediga.tictactoe.model;

public class Player {
    Piece piece;
    int noOfPieces;
    String name;

    public Player(String name, Piece piece, int noOfPieces) {
        this.piece = piece;
        this.noOfPieces = noOfPieces;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public int getNoOfPieces() {
        return noOfPieces;
    }

    public void setNoOfPieces(int noOfPieces) {
        this.noOfPieces = noOfPieces;
    }
}
