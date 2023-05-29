package edu.uj.po.interfaces;

import java.util.List;
import java.util.Optional;

import edu.uj.po.src.Board;
import edu.uj.po.src.Piece;
import edu.uj.po.src.PieceBuilder;


public class ChessSolver implements Setup, Solver {

    private Board board;
    private PieceBuilder pieceBuilder;

    public ChessSolver() {
        board = new Board();
        pieceBuilder = new PieceBuilder();
    }

	public Optional<Move> findMateInOneMove(Color color) {
        return board.findMateInOneMove(color);
    }

	public Optional<Move> findStalemateInOneMove(Color color) {
        return board.findStalemateInOneMove(color);
    }

    @Override
    public void reset() {
        board = new Board();
    }

    @Override
    public void addChessPiece(Position position, Color color, ChessPiece piece) {
        pieceBuilder.setColor(color);
        pieceBuilder.setPosition(position);
        pieceBuilder.setType(piece);
        Piece newPiece = pieceBuilder.build(board);
        board.addChessPiece(newPiece);
    }

    // TODO - delete -> this is only for testing
    public List<Move> returnify() {
        Piece firstPiece = board.getPieces().get(0);
        firstPiece.discoverPossibleMoves();
        return firstPiece.getPossibleMoves();
    }
}
