package edu.uj.po.src.MoveStrategies;

import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import edu.uj.po.interfaces.Position;
import edu.uj.po.interfaces.Move;
import edu.uj.po.src.BoundsValidator;
import edu.uj.po.src.Direction;
import edu.uj.po.src.Board;
import edu.uj.po.src.Piece;
import edu.uj.po.src.interfaces.MoveStrategy;

public class RookMove implements MoveStrategy {

    private Piece piece;
    private Position currentPosition;

    public RookMove(Piece piece) {
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
        List<Move> moves = new ArrayList<Move>();
        for (int i = 1; i < 8; i++) {
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
            } else break;
        }
        return moves;
    }

    @Override
    public List<Move> discoverPossibleMoves(Piece piece, Board board) {
        List<Move> moves = new ArrayList<Move>();
        List<Direction> rookDirections = List.of(Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST);

        for (Direction direction : rookDirections) {
            moves.addAll(scanDirectionForMoves(direction, board));
        }

        return moves;
    }
}
