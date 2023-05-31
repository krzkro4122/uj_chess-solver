package edu.uj.po.src.MoveStrategies;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.uj.po.interfaces.ChessPiece;
import edu.uj.po.interfaces.Move;
import edu.uj.po.interfaces.Position;
import edu.uj.po.interfaces.Rank;
import edu.uj.po.src.BoundsValidator;
import edu.uj.po.src.Direction;
import edu.uj.po.src.Piece;
import edu.uj.po.src.interfaces.MoveStrategy;

public class PawnMove implements MoveStrategy {

    private Piece piece;

    public PawnMove(Piece piece) {
        this.piece = piece;
    }

    private boolean hasAlreadyMoved() {
        if (piece.getType() != ChessPiece.PAWN)
            throw new IllegalCallerException("This ain't no pawn! Stop calling me...");
        switch (piece.getColor()) {
            case WHITE: if (piece.getPosition().rank() == Rank.SECOND) return false;
            case BLACK: if (piece.getPosition().rank() == Rank.SEVENTH) return false;
        }
        return true;
    }

    private Optional<Position> createPosition(int amount, Direction direction) {
        return BoundsValidator.validatePositionBounds(piece.getPosition(), direction, amount);
    }

    private Move createMove(Position destination) {
        return new Move(
            piece.getPosition(),
            destination
        );
    }

    private List<Move> leftAttack() {
        List<Move> moves = new ArrayList<Move>();

        Optional<Position> leftAttackPossiblePosition = createPosition(1, Direction.NORTH_WEST);

            if (leftAttackPossiblePosition.isEmpty())
                return null;

            Position leftAttackPosition = leftAttackPossiblePosition.get();
            Optional<Piece> potentialPiece = piece.checkWhoIsAt(leftAttackPosition);
            if (potentialPiece.isPresent()) {
                Piece detectedPiece = potentialPiece.get();
                if (detectedPiece.getColor() != piece.getColor()) {
                    moves.add(new Move(piece.getPosition(), leftAttackPosition));
                }
            }
        return moves;
    }

    private List<Move> rightAttack() {
        List<Move> moves = new ArrayList<Move>();

        Optional<Position> rightAttackPossiblePosition = createPosition(1, Direction.NORTH_EAST);

            if (rightAttackPossiblePosition.isEmpty())
                return null;

            Position rightAttackPosition = rightAttackPossiblePosition.get();
            Optional<Piece> potentialPiece = piece.checkWhoIsAt(rightAttackPosition);
            if (potentialPiece.isPresent()) {
                Piece detectedPiece = potentialPiece.get();
                if (detectedPiece.getColor() != piece.getColor()) {
                    moves.add(new Move(piece.getPosition(), rightAttackPosition));
                }
            }
        return moves;
    }

    @Override
    public List<Move> discoverPossibleMoves(Piece piece) {
        this.piece = piece;
        List<Move> moves = new ArrayList<Move>();

        // Attacks
        moves.addAll(leftAttack());
        moves.addAll(rightAttack());

        // Simple moves
        Optional<Position> possiblePositionInFront = createPosition(1, Direction.NORTH);
        if (possiblePositionInFront.isEmpty())
            return moves;
        Position positionInFront = possiblePositionInFront.get();
        Optional<Piece> potentialPiece = piece.checkWhoIsAt(positionInFront);
        if (potentialPiece.isEmpty()) {
            moves.add(createMove(positionInFront));
            possiblePositionInFront = createPosition(2, Direction.NORTH);
            if (possiblePositionInFront.isEmpty())
                return moves;
            positionInFront = possiblePositionInFront.get();
            potentialPiece = piece.checkWhoIsAt(positionInFront);
            if (!hasAlreadyMoved() && potentialPiece.isEmpty()) {
                moves.add(createMove(positionInFront));
            }
        }

        return moves;
    }
}
