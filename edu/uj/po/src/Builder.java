package edu.uj.po.src;

import edu.uj.po.interfaces.ChessPiece;
import edu.uj.po.interfaces.Color;
import edu.uj.po.interfaces.Position;

public interface Builder {
    public void setColor(Color color);
    public void setType(ChessPiece type);
    public void setPosition(Position position);
    public void discoverPossibleMoves();
    public Piece build();
}
