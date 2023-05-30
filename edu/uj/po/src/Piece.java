package edu.uj.po.src;

import java.util.List;
import java.util.Optional;

import edu.uj.po.interfaces.ChessPiece;
import edu.uj.po.interfaces.Color;
import edu.uj.po.interfaces.Move;
import edu.uj.po.interfaces.Position;
import edu.uj.po.interfaces.Rank;
import edu.uj.po.src.interfaces.MoveStrategy;
import edu.uj.po.src.interfaces.SearchHandler;

public class Piece implements SearchHandler {

    Color color;
    Board _board;
    ChessPiece type;
    Position position;
    Piece previousPiece;
    MoveStrategy moveStrategy;
    private List<Move> possibleMoves;

    @Override
    public Optional<Move> findMate() {
        BoardMemento backup = _board.saveSnapshot();
        discoverPossibleMoves();
        for (Move move : getPossibleMoves()) {
            _board.loadSnapshot(backup);
            this.setPosition(move.finalPosition());

            List<Piece> enemies =_board.getColoredPieces(getOppositeColor());
            for (Piece enemy : enemies) {
                enemy.discoverPossibleMoves();

                for (Move enemyMove : enemy.getPossibleMoves()) {
                    enemy.setPosition(enemyMove.finalPosition());

                    for (Piece ally : _board.getColoredPieces(color)) {
                        ally.discoverPossibleMoves();

                        for (Move allyMove : ally.getPossibleMoves()) {
                            Piece enemyKing = _board.getKing(getOppositeColor());

                            if (allyMove.finalPosition().equals(enemyKing.getPosition())) {
                                return Optional.of(move);
                            }
                        }
                    }
                }
            }
        }
        _board.loadSnapshot(backup);

        // Resp. chain
        if (this.previousPiece != null) {
            return this.previousPiece.findMate();
        } else return Optional.empty();
    }

    @Override
    public Optional<Move> findStaleMate() {
        BoardMemento backup = _board.saveSnapshot();
        discoverPossibleMoves();
        for (Move move : getPossibleMoves()) {
            _board.loadSnapshot(backup);
            this.setPosition(move.finalPosition());

            List<Piece> enemies =_board.getColoredPieces(getOppositeColor());
            for (Piece enemy : enemies) {

                enemy.discoverPossibleMoves();
                List<Move> enemyMoves = enemy.getPossibleMoves();
                if (enemyMoves.isEmpty()) {
                    return Optional.of(move);
                }
            }
        }
        _board.loadSnapshot(backup);

        // Resp. chain
        if (this.previousPiece != null) {
            return this.previousPiece.findMate();
        } else return Optional.empty();
    }

    public Position getPosition() {
        return position;
    }

    public Color getColor() {
        return color;
    }

    public Color getOppositeColor() {
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public ChessPiece getType() {
        return type;
    }

    public void setPosition(Position position) {
        this.position = position;

        // Promotion
        if (type == ChessPiece.PAWN) {
            boolean whitePromotable = color == Color.WHITE && position.rank() == Rank.EIGHTH;
            boolean blackPromotable = color == Color.BLACK && position.rank() == Rank.FIRST;
            boolean promotable = whitePromotable || blackPromotable;
            if (promotable) { this.type = ChessPiece.QUEEN; }
        }
    }

    public List<Move> getPossibleMoves() {
        return possibleMoves;
    }

    public void discoverPossibleMoves() {
        possibleMoves = moveStrategy.discoverPossibleMoves(this, _board);
    }
}
