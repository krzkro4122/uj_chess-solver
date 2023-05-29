package edu.uj.po.src.MoveStrategies;

import java.util.List;
import java.util.Optional;

import edu.uj.po.interfaces.ChessPiece;
import edu.uj.po.interfaces.File;
import edu.uj.po.interfaces.Move;
import edu.uj.po.interfaces.MoveStrategy;
import edu.uj.po.interfaces.Position;
import edu.uj.po.interfaces.Rank;
import edu.uj.po.src.Board;
import edu.uj.po.src.Direction;
import edu.uj.po.src.Piece;

public class PawnMove implements MoveStrategy {

    private Piece piece;
    private Position currentPosition;

    PawnMove(Piece piece) {
        this.piece = piece;
        currentPosition = piece.getPosition();
    }

    private boolean hasAlreadyMoved() {
        if (piece.getType() != ChessPiece.PAWN)
            throw new IllegalCallerException("This ain't no pawn! Stop calling me...");
        switch (piece.getColor()) {
            case WHITE: if (currentPosition.rank() == Rank.SECOND) return false;
            case BLACK: if (currentPosition.rank() == Rank.SEVENTH) return false;
        }
        return true;
    }

    private Position createPosition(int amount, Direction direction) {
        assert amount > 0 && amount <= 1;
        File startFile = currentPosition.file();
        Rank startRank = currentPosition.rank();
        File[] files = File.values();
        Rank[] ranks = Rank.values();
        return new Position(
            files[(startFile.ordinal() + amount * direction.getFileCoef()) % files.length],
            ranks[(startRank.ordinal() + amount * direction.getRankCoef()) % ranks.length]
            );
    }

    private Move createMove(int amount, Direction direction) {
        assert amount > 0 && amount <= 1;
        Position destination = createPosition(amount, direction);
        return new Move(
            currentPosition,
            destination
            );
    }

    @Override
    public List<Move> discoverAllMoves(Piece piece, Board board) {
        List<Move> moves = List.of();

        // Attacks
        {
            Position leftAttackPosition = createPosition(1, Direction.NORTH_WEST);
            Optional<Piece> potentialPiece = board.checkPosition(leftAttackPosition);
            if (potentialPiece.isPresent()) {
                Piece detectedPiece = potentialPiece.get();
                if (detectedPiece.getColor() != piece.getColor()) {
                    moves.add(new Move(currentPosition, leftAttackPosition));
                }
            }
        }

        {
            Position rightAttackPosition = createPosition(1, Direction.NORTH_EAST);
            Optional<Piece> potentialPiece = board.checkPosition(rightAttackPosition);
            if (potentialPiece.isPresent()) {
                Piece detectedPiece = potentialPiece.get();
                if (detectedPiece.getColor() != piece.getColor()) {
                    moves.add(new Move(currentPosition, rightAttackPosition));
                }
            }
        }

        // Simple moves
        Position positionInFront = createPosition(1, Direction.NORTH);
        Optional<Piece> potentialPiece = board.checkPosition(positionInFront);
        if (potentialPiece.isEmpty()) {
            moves.add(createMove(1, Direction.NORTH));
            positionInFront = createPosition(2, Direction.NORTH);
            potentialPiece = board.checkPosition(positionInFront);
            if (!hasAlreadyMoved() && potentialPiece.isEmpty()) {
                moves.add(createMove(2, Direction.NORTH));
            }
        }

        return moves;
    }
}
