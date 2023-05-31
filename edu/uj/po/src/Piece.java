package edu.uj.po.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.uj.po.interfaces.ChessPiece;
import edu.uj.po.interfaces.Color;
import edu.uj.po.interfaces.Move;
import edu.uj.po.interfaces.Position;
import edu.uj.po.interfaces.Rank;
import edu.uj.po.src.MoveStrategies.QueenMove;
import edu.uj.po.src.interfaces.MoveStrategy;
import edu.uj.po.src.interfaces.SearchHandler;

public class Piece implements SearchHandler {

    Piece root;
    Piece next;
    Color color;
    ChessPiece type;
    Position position;
    boolean isCaptured = false;
    MoveStrategy moveStrategy;
    private List<Move> possibleMoves;

    @Override
    public Optional<Move> findMate() {
        Position initialPosition = getPosition();
        for (Move move : getPossibleMoves()) {
            setPosition(move.finalPosition());

            List<Piece> enemies = getEnemies();
            for (Piece enemy : enemies) {
                List<Move> enemyMoves = enemy.getPossibleMoves();
                Position enemyInitialPosition = enemy.getPosition();
                for (Move enemyMove : enemyMoves) {
                    enemy.setPosition(enemyMove.finalPosition());

                    if (isKingInCheck(getOppositeColor())) {
                        enemy.setPosition(enemyInitialPosition);
                        setPosition(initialPosition);
                        return Optional.of(move);
                    }

                    enemy.setPosition(enemyInitialPosition);
                }
            }
            if (isKingInCheck(getOppositeColor(color))) {
                setPosition(initialPosition);
                return Optional.of(move);
            }
            setPosition(initialPosition);
        }

        // Resp. chain
        if (this.next == null) {
            return Optional.empty();
        } else return this.next.findMate();
    }

    @Override
    public Optional<Move> findStaleMate() {
        Position initialPosition = getPosition();
        for (Move move : getPossibleMoves()) {
            setPosition(move.finalPosition());

            List<Piece> enemies = getEnemies();
            for (Piece enemy : enemies) {
                if (enemy.getPossibleMoves().isEmpty()) {
                    if (!isKingInCheck(getOppositeColor())) {
                        setPosition(initialPosition);
                        return Optional.of(move);
                    }
                }
            }
            setPosition(initialPosition);
        }

        // Resp. chain
        if (this.next == null) {
            return Optional.empty();
        } else return this.next.findMate();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;

        // Attack
        Optional<Piece> possiblePiece = checkWhoIsAt(position);
        if (possiblePiece.isPresent()) {
            Piece attackedPiece = possiblePiece.get();
            if (attackedPiece.getColor() != color) {
                attackedPiece.isCaptured = true;
            }
        }

        // Promotion
        if (type == ChessPiece.PAWN) {
            boolean whitePromotable = color == Color.WHITE && position.rank() == Rank.EIGHTH;
            boolean blackPromotable = color == Color.BLACK && position.rank() == Rank.FIRST;
            boolean promotable = whitePromotable || blackPromotable;
            if (promotable) {
                type = ChessPiece.QUEEN;
                moveStrategy = new QueenMove(this);
            }
        }
    }

    public Color getColor() {
        return color;
    }

    public Color getOppositeColor() {
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }

    public Color getOppositeColor(Color color) {
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
        discoverPossibleMoves();
        return possibleMoves;
    }

    public List<Piece> getColoredPieces(Color color) {
        List<Piece> coloredPieces = new ArrayList<Piece>();
        Piece currentPiece = root;
        while (currentPiece != null) {
            if (currentPiece.getColor() == color && !currentPiece.isCaptured) {
                coloredPieces.add(currentPiece);
            }
            currentPiece = currentPiece.next;
        }
        return coloredPieces;
    }

    public List<Piece> getAllies() {
        return getColoredPieces(color)
            .stream()
            .filter(piece -> !piece.isCaptured)
            .toList();
    }

    public List<Piece> getEnemies() {
        return getColoredPieces(getOppositeColor())
            .stream()
            .filter(piece -> !piece.isCaptured)
            .toList();
    }

    public Piece getKing(Color color) {
        Piece currentPiece = root;
        while (currentPiece != null) {
            boolean isKing = currentPiece.getType() == ChessPiece.KING;
            boolean isTheRightColor = currentPiece.getColor() == color;
            if (isKing && isTheRightColor) {
                return currentPiece;
            }
            currentPiece = currentPiece.next;
        }
        return null;
    }

    public Optional<Piece> checkWhoIsAt(Position position) {
        Piece currentPiece = root;
        while (currentPiece != null) {
            if (currentPiece.getPosition().equals(position) && !currentPiece.isCaptured) {
                return Optional.of(currentPiece);
            }
            currentPiece = currentPiece.next;
        }
        return Optional.empty();
    }

    public boolean isKingInCheck(Color color) {
        Position kingPosition = getKing(color).getPosition();
        List<Piece> enemies = getColoredPieces(getOppositeColor(color));
        for (Piece enemy : enemies) {
            List<Move> enemyMoves = enemy.getMoveStrategy().discoverPossibleMoves(enemy);
            for (Move enemyMove : enemyMoves) {
                if (enemyMove.finalPosition().equals(kingPosition)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<Move> pruneSuicidalMoves(Piece piece, List<Move> moves) {
        Position initialPosition = piece.getPosition();
        List<Move> prunedMoves =  new ArrayList<Move>(moves);

        for (Move move : moves) {
            piece.setPosition(move.finalPosition());

            boolean kingInCheck = isKingInCheck(piece.color);

            if (kingInCheck) { prunedMoves.remove(move); }
            piece.setPosition(initialPosition);
        }

        piece.setPosition(initialPosition);
        return prunedMoves;
    }

    public void discoverPossibleMoves() {
        possibleMoves = moveStrategy.discoverPossibleMoves(this);
        possibleMoves = pruneSuicidalMoves(this, possibleMoves);
    }
}
