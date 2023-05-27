package edu.uj.po.interfaces;

public interface Builder {
    public void setColor(Color color);
    public void setType(ChessPiece type);
    public void setPosition(Position position);
    public void discoverPossibleMoves();
    public Piece build();
}
