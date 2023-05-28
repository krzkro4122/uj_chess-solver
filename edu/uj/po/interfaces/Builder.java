package edu.uj.po.interfaces;

import edu.uj.po.src.Piece;

public interface Builder {
    public void setColor(Color color);
    public void setType(ChessPiece type);
    public void setPosition(Position position);
    public Piece build();
}
