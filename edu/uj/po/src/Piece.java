package edu.uj.po.src;

import java.util.List;
import java.util.Optional;

import edu.uj.po.interfaces.ChessPiece;
import edu.uj.po.interfaces.Color;
import edu.uj.po.interfaces.Move;
import edu.uj.po.interfaces.Position;
import edu.uj.po.src.interfaces.Builder;
import edu.uj.po.src.interfaces.MoveStrategy;
import edu.uj.po.src.interfaces.SearchHandler;

public class Piece implements SearchHandler {

    Color color;
    Board _board;
    ChessPiece type;
    Position position;
    Piece previousPiece;
    MoveStrategy moveStrategy;
    private List<Move> possibleMoves;

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

    public Color getColor() {
        return color;
    }

    public ChessPiece getType() {
        return type;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public List<Move> getPossibleMoves() {
        return possibleMoves;
    }

    public void discoverPossibleMoves() {
        possibleMoves = moveStrategy.discoverPossibleMoves(this, _board);
    }
}
