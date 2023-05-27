package edu.uj.po.src;

import java.util.List;

public class BoardMemento {
    private List<Piece> pieces;

    public BoardMemento(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public List<Piece> getBoard() {
        return pieces;
    }
}