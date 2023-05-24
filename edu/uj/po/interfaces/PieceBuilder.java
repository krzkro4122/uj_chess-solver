package edu.uj.po.interfaces;

public class PieceBuilder implements Builder {

    private Piece lastPiece;

    @Override
    public void setColor(Color color) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setColor'");
    }

    @Override
    public void setType(ChessPiece type) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setType'");
    }

    @Override
    public void setPosition(Position position) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPosition'");
    }

    @Override
    public void discoverPossibleMoves() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'discoverPossibleMoves'");
    }

    @Override
    public void setMoveValidator(MoveValidator moveValidator) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMoveValidator'");
    }

}
