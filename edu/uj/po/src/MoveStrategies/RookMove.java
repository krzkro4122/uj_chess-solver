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
import edu.uj.po.src.BoundsValidator;
import edu.uj.po.src.Direction;
import edu.uj.po.src.Piece;

public class RookMove implements MoveStrategy {

    private Piece piece;
    private Position currentPosition;

    RookMove(Piece piece) {
        this.piece = piece;
        currentPosition = piece.getPosition();
    }

    private Optional<Position> createPosition(int amount, Direction direction) {
        return BoundsValidator.validatePositionBounds(currentPosition, direction, amount);
    }

    private Move createMove(Position destination) {
        return new Move(
            currentPosition,
            destination
        );
    }

    private List<Move> scanDirectionForMoves(Direction direction, Board board) {
        List<Move> moves = List.of();
        for (int i = 0; i < 8; i++) {
            Optional<Position> possibleDestination = createPosition(i, direction);
            if (possibleDestination.isPresent()) {
                Position destination = possibleDestination.get();
                Optional<Piece> possiblePiece = board.checkPosition(possibleDestination.get());
                if (possiblePiece.isEmpty()) {
                    moves.add(createMove(destination));
                } else if (possiblePiece.get().getColor() != piece.getColor()) {
                    moves.add(createMove(destination));
                    break;
                } else {
                    break;
                }
            }
        }
    }

    @Override
    public List<Move> discoverPossibleMoves(Piece piece, Board board) {
        List<Move> moves = List.of();
        List<Direction> rookDirections = List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);

        for (Direction direction : rookDirections) {
            moves.addAll(scanDirectionForMoves(direction, board));
        }

        return moves;
}
