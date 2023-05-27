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


    public class PieceBuilder implements Builder {

        private Color color;
        private ChessPiece type;
        private Position position;
        private List<Move> possibleMoves;
        private Piece lastPiece;

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
        public void discoverPossibleMoves() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'discoverPossibleMoves'");
        }

        @Override
        public Piece build() {
            Piece piece = new Piece();
            piece.color = color;
            piece.type = type;
            piece.position = position;
            piece.possibleMoves = possibleMoves;

            if (lastPiece != null) {
                lastPiece.nextPiece = piece;
                lastPiece = piece;
            } else {
                lastPiece = piece;
            }

            return piece;
        }
    }
}
