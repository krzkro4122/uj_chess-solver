package edu.uj.po.src;

import java.util.ArrayList;
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

    Piece root;
    Piece next;
    Color color;
    ChessPiece type;
    Position position;
    MoveStrategy moveStrategy;
    private List<Move> possibleMoves;

    @Override
    public Optional<Move> findMate() {
        Position initialPosition = getPosition();
        discoverPossibleMoves();
        for (Move move : getPossibleMoves()) {
            setPosition(move.finalPosition());
            List<Piece> enemies = getEnemies();

            for (Piece enemy : enemies) {
                enemy.discoverPossibleMoves();
                Position enemeyInitialPosition = enemy.getPosition();
                for (Move enemyMove : enemy.getPossibleMoves()) {
                    enemy.setPosition(enemyMove.finalPosition());

                    for (Piece ally : getAllies()) {
                        ally.discoverPossibleMoves();

                        for (Move allyMove : ally.getPossibleMoves()) {
                            Piece enemyKing = getKing(getOppositeColor());

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
        if (this.next != null) {
            return this.next.findMate();
        } else return Optional.empty();
    }

    @Override
    public Optional<Move> findStaleMate() {
        Position initialPosition = getPosition();
        discoverPossibleMoves();
        for (Move move : getPossibleMoves()) {
            setPosition(move.finalPosition());
            List<Piece> enemies = getColoredPieces(getOppositeColor());

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
        if (this.next != null) {
            return this.next.findMate();
        } else return Optional.empty();
    }

    public Position getPosition() {
        return position;
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

    public Color getColor() {
        return color;
    }

    public Color getOppositeColor() {
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public ChessPiece getType() {
        return type;
    }

    public Piece getRoot() {
        return root;
    }

    public MoveStrategy getMoveStrategy() {
        return moveStrategy;
    }

    public List<Move> getPossibleMoves() {
        if (possibleMoves == null) { discoverPossibleMoves(); }
        return possibleMoves;
    }

    public List<Piece> getColoredPieces(Color color) {
        List<Piece> coloredPieces = new ArrayList<Piece>();
        Piece currentPiece = root;
        while (currentPiece != null) {
            if (currentPiece.getColor() == color)
                coloredPieces.add(currentPiece);
            currentPiece = currentPiece.next;
        }
        return coloredPieces;
    }

    public List<Piece> getAllies() {
        return getColoredPieces(color);
    }

    public List<Piece> getEnemies() {
        return getColoredPieces(getOppositeColor());
    }

    public Piece getKing(Color color) {
        Piece currentPiece = root;
        while (currentPiece != null) {
            boolean isKing = currentPiece.getType() == ChessPiece.KING;
            boolean isTheRightColor = currentPiece.getColor() == color;
            if (isKing && isTheRightColor)
                return currentPiece;
            currentPiece = currentPiece.next;
        }
        return null;
    }

    public Optional<Piece> checkPosition(Position position) {
        Piece currentPiece = root;
        while (currentPiece != null) {
            if (currentPiece.getPosition() == position)
                return Optional.of(currentPiece);
            currentPiece = currentPiece.next;
        }
        return Optional.empty();
    }

    public boolean checkForCheck(Color color, List<Move> allyMoves) {
        Piece king = getKing(color);
        List<Piece> enemyPieces = getColoredPieces(king.getOppositeColor());
        for (Piece enemyPiece : enemyPieces) {

            List<Move> potentialEnemyMoves = enemyPiece.getMoveStrategy().discoverPossibleMoves(enemyPiece);
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

    public List<Move> pruneSuicidalMoves(Piece piece, List<Move> moves) {
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
        possibleMoves = moveStrategy.discoverPossibleMoves(this);
        possibleMoves = pruneSuicidalMoves(this, possibleMoves);
    }
}
