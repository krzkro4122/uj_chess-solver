package edu.uj.po.src;

import java.util.List;
import java.util.Optional;

import edu.uj.po.interfaces.Color;
import edu.uj.po.interfaces.Move;
import edu.uj.po.interfaces.Solver;

public class Board implements Solver {
    private List<Piece> pieces;
    private BoardMemento memento;

    public void loadSnapshot(BoardMemento memento) {
        pieces = memento.getBoard();
    }

    public void saveSnapshot() {
        memento = new BoardMemento(pieces);
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void addChessPiece(Piece piece) {
        pieces.add(piece);
    }

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
