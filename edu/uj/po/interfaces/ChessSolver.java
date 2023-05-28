package edu.uj.po.interfaces;

import java.util.Optional;

import edu.uj.po.src.Board;
import edu.uj.po.src.Piece;
import edu.uj.po.src.Piece.PieceBuilder;


class ChessSolver implements Setup, Solver {

    private Board board;
    private PieceBuilder pieceBuilder;

	public Optional<Move> findMateInOneMove(Color color) {
        return board.findMateInOneMove(color);
    }

	public Optional<Move> findStalemateInOneMove(Color color) {
        return board.findStalemateInOneMove(color);
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }

    @Override
    public void addChessPiece(Position position, Color color, ChessPiece piece) {
        pieceBuilder.setColor(color);
        pieceBuilder.setPosition(position);
        pieceBuilder.setType(piece);
        Piece newPiece = pieceBuilder.build();
        board.addChessPiece(newPiece);
    }
}
