package edu.uj.po.interfaces;

import java.util.List;

import edu.uj.po.src.Board;
import edu.uj.po.src.Piece;

public interface MoveStrategy {
    List<Move> discoverAllMoves(Piece piece, Board board);
}
