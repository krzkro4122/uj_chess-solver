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
        Position initialPosition = getPosition();
        discoverPossibleMoves();
        for (Move move : getPossibleMoves()) {
            setPosition(move.finalPosition());
            List<Piece> enemies = _board.getColoredPieces(getOppositeColor());

            for (Piece enemy : enemies) {
                enemy.discoverPossibleMoves();
                Position enemeyInitialPosition = enemy.getPosition();
                for (Move enemyMove : enemy.getPossibleMoves()) {
                    enemy.setPosition(enemyMove.finalPosition());

                    for (Piece ally : _board.getColoredPieces(color)) {
                        ally.discoverPossibleMoves();

                        for (Move allyMove : ally.getPossibleMoves()) {
                            Piece enemyKing = _board.getKing(getOppositeColor());

                            if (allyMove.finalPosition().equals(enemyKing.getPosition())) {
                                setPosition(initialPosition);
                                enemy.setPosition(enemyMove.finalPosition());
                                return Optional.of(move);
                            }
                        }
                    }
                    enemy.setPosition(enemeyInitialPosition);
                }
            }
            setPosition(initialPosition);
        }
        setPosition(initialPosition);

        // Resp. chain
        if (this.previousPiece != null) {
            return this.previousPiece.findMate();
        } else return Optional.empty();
    }

    @Override
    public Optional<Move> findStaleMate() {
        Position initialPosition = getPosition();
        discoverPossibleMoves();
        for (Move move : getPossibleMoves()) {
            setPosition(move.finalPosition());
            List<Piece> enemies =_board.getColoredPieces(getOppositeColor());

            for (Piece enemy : enemies) {
                enemy.discoverPossibleMoves();
                List<Move> enemyMoves = enemy.getPossibleMoves();

                if (enemyMoves.isEmpty()) {
                    setPosition(initialPosition);
                    return Optional.of(move);
                }
            }
            setPosition(initialPosition);
        }
        setPosition(initialPosition);

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

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
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
        if (possibleMoves == null) {
            possibleMoves = moveStrategy.discoverPossibleMoves(this, _board);
        }
        return possibleMoves;
    }

    public boolean checkForCheck(Color color, List<Move> allyMoves) {
        Piece king = _board.getKing(color);
        List<Piece> enemyPieces = _board.getColoredPieces(king.getOppositeColor());
        for (Piece enemyPiece : enemyPieces) {

            List<Move> potentialEnemyMoves = enemyPiece.getMoveStrategy().discoverPossibleMoves(enemyPiece, _board);
            for (Move potentialEnemyMove : potentialEnemyMoves) {
                for (Move allyMove : allyMoves) {
                    if (potentialEnemyMove.finalPosition() == allyMove.finalPosition()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }


    public List<Move> pruneSuicidalMoves(Piece piece, Board board, List<Move> moves) {
        Position initialPosition = piece.getPosition();

        for (Move move : moves) {
            piece.setPosition(move.finalPosition());

            boolean kingInCheck = checkForCheck(piece.getColor(), moves);
            if (kingInCheck) moves.remove(move);
        }

        piece.setPosition(initialPosition);
        return moves;
    }

    public void discoverPossibleMoves() {
        possibleMoves = moveStrategy.discoverPossibleMoves(this, _board);
        possibleMoves = pruneSuicidalMoves(this, _board, possibleMoves);
    }
}
