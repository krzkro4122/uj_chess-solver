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

    public void snapshotBoard() {
        this.memento = new BoardMemento(pieces);
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void addChessPiece(Piece piece) {
        pieces.add(piece);
    }

    @Override
    public Optional<Move> findMateInOneMove(Color color) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findMateInOneMove'");
    }

    @Override
    public Optional<Move> findStalemateInOneMove(Color color) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findStalemateInOneMove'");
    }
}
