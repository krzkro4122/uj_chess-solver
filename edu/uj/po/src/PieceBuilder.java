package edu.uj.po.src;

import edu.uj.po.interfaces.Builder;
import edu.uj.po.interfaces.ChessPiece;
import edu.uj.po.interfaces.Color;
import edu.uj.po.interfaces.MoveStrategy;
import edu.uj.po.interfaces.Position;
import edu.uj.po.src.MoveStrategies.*;

public class PieceBuilder implements Builder {

    private Color color;
    private ChessPiece type;
    private Position position;
    private Piece previousPiece;
    private MoveStrategy moveStrategy;

    public PieceBuilder() {}

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

    private MoveStrategy determineMoveStrategy(Piece piece) {
        MoveStrategy typeStrategy;
        switch (type) {
            case KING:
                typeStrategy = new KingMove(piece);
                break;
            case ROOK:
                typeStrategy = new RookMove(piece);
                break;
            case BISHOP:
                typeStrategy = new BishopMove(piece);
                break;
            case QUEEN:
                typeStrategy = new QueenMove(piece);
                break;
            case KNIGHT:
                typeStrategy = new KnightMove(piece);
                break;
            case PAWN:
                typeStrategy = new PawnMove(piece);
                break;
            default:
                throw new IllegalArgumentException("Invalid piece type");
        }
        return typeStrategy;
    }

    @Override
    public Piece build(Board board) {
        Piece piece = new Piece();
        piece.color = color;
        piece.type = type;
        piece.position = position;
        piece._board = board;
        moveStrategy = determineMoveStrategy(piece);
        piece.moveStrategy = moveStrategy;
        if (previousPiece != null) {
            piece.previousPiece = previousPiece;
        }
        previousPiece = piece;
        return piece;
    }
}