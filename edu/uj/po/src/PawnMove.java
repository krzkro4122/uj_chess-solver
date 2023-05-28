package edu.uj.po.src;

import java.io.File;
import java.util.List;

import edu.uj.po.interfaces.ChessPiece;
import edu.uj.po.interfaces.Color;
import edu.uj.po.interfaces.Move;
import edu.uj.po.interfaces.MoveStrategy;
import edu.uj.po.interfaces.Position;
import edu.uj.po.interfaces.Rank;

public class PawnMove implements MoveStrategy {

    private Piece piece;

    PawnMove(Piece piece) {
        this.piece = piece;
    }

    private boolean hasAlreadyMoved() {
        if (piece.getType() != ChessPiece.PAWN)
            return false;
        switch (piece.getColor()) {
            case WHITE: if (piece.getPosition().rank() == Rank.SECOND) return true;
            case BLACK: if (piece.getPosition().rank() == Rank.SEVENTH) return true;
        }
        return false;
    }

    private attack() {

    }

    @Override
    public List<Move> discoverAllMoves(Piece piece) {

        // TODO - handle "en passant" for the defending side

        if ()

        if (hasAlreadyMoved()) {

        } else {
            return
        }
    }
}
