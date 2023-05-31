package edu.uj.po.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.uj.po.interfaces.ChessPiece;
import edu.uj.po.interfaces.Color;
import edu.uj.po.interfaces.Move;
import edu.uj.po.interfaces.Position;
import edu.uj.po.interfaces.Solver;

public class Board implements Solver {
    private List<Piece> pieces;
    private BoardMemento memento;

    public Board() {
        this.pieces = new ArrayList<Piece>();
        this.memento = new BoardMemento(pieces);
    }

    public void loadSnapshot(BoardMemento memento) {
        pieces = memento.getBoard();
    }

    public BoardMemento saveSnapshot() {
        memento = new BoardMemento(pieces);
        return memento;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public List<Piece> getColoredPieces(Color color) {
        return pieces
            .stream()
            .filter(p -> p.getColor() == color)
            .toList();
    }

    public void addChessPiece(Piece piece) {
        pieces.add(piece);
    }

    // public Optional<Piece> checkPosition(Position position) {
    //     return pieces
    //         .stream()
    //         .filter(p -> p.getPosition().equals(position))
    //         .findFirst();
    // }

    @Override
    public Optional<Move> findMateInOneMove(Color color) {
        Piece coloredRepresentant = pieces
            .stream()
            .filter(p -> p.getColor() == color)
            .findFirst()
            .get();
        return coloredRepresentant.findMate();
    }

    @Override
    public Optional<Move> findStalemateInOneMove(Color color) {
        Piece coloredRepresentant = pieces
            .stream()
            .filter(p -> p.getColor() == color)
            .findFirst()
            .get();
        return coloredRepresentant.findStaleMate();
    }
}
