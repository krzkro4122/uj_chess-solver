package edu.uj.po.interfaces;

import java.util.List;
import java.util.Optional;

public class Piece implements SearchHandler {

    private Color color;
    private ChessPiece type;
    private Position position;
    private List<Move> possibleMoves;
    private Piece nextPiece;

    @Override
    public Optional<Move> findMate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findMate'");
    }

    @Override
    public Optional<Move> findStaleMate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findStaleMate'");
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
