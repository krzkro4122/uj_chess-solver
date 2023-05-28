package edu.uj.po.src;

import java.util.List;
import java.util.Optional;

import edu.uj.po.interfaces.Builder;
import edu.uj.po.interfaces.ChessPiece;
import edu.uj.po.interfaces.Color;
import edu.uj.po.interfaces.Move;
import edu.uj.po.interfaces.Position;
import edu.uj.po.interfaces.SearchHandler;

public class Piece implements SearchHandler {

    private Color color;
    private ChessPiece type;
    private Position position;
    private List<Move> possibleMoves;
    private Piece lastPiece;

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
        switch (type) {
            case BISHOP:    discoverBishopMoves(); break;
            case KING:      discoverKingMoves(); break;
            case KNIGHT:    discoverKnightMoves(); break;
            case PAWN:      discoverPawnMoves(); break;
            case QUEEN:     discoverQueenMoves(); break;
            case ROOK:      discoverRookMoves(); break;
            default:        throw new IllegalArgumentException("Unknown type of piece");
        }
    }

    private void discoverQueenMoves() {
        discoverBishopMoves();
        discoverRookMoves();
    }

    private void discoverPawnMoves() {
        // TODO - HANDLE "en passant"

    }

    private void discoverKnightMoves() {

    }

    private void discoverKingMoves() {

    }

    private void discoverBishopMoves() {

    }

    private void discoverRookMoves() {

    }

    public class PieceBuilder implements Builder {

        private Color color;
        private Piece lastPiece;
        private ChessPiece type;
        private Position position;

        @Override
        public void setColor(Color color) {
            this.color = color;
        }

        @Override
        public void setType(ChessPiece type) {
            this.type = type;
        }

        @Override
        public void setPosition(Position position) {
            this.position = position;
        }

        @Override
        public Piece build() {
            Piece piece = new Piece();
            piece.color = color;
            piece.type = type;
            piece.position = position;

            if (lastPiece != null) {
                piece.lastPiece = lastPiece;
            }

            lastPiece = piece;
            return piece;
        }
    }
}
