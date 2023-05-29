package edu.uj.po.src;

import java.util.Optional;

import edu.uj.po.interfaces.File;
import edu.uj.po.interfaces.Position;
import edu.uj.po.interfaces.Rank;

public class BoundsValidator {
    public static Optional<Position> validatePositionBounds(Position currentPosition, Direction direction, int amount) {
        File startFile = currentPosition.file();
        Rank startRank = currentPosition.rank();
        File[] files = File.values();
        Rank[] ranks = Rank.values();

        int destinationFile = startFile.ordinal() + amount * direction.getFileCoef();
        int destinationRank = startRank.ordinal() + amount * direction.getRankCoef();
        if (    destinationFile >= files.length || destinationFile < 0
            ||  destinationRank >= ranks.length || destinationRank < 0)
            return null;
        return Optional.of(new Position(
            files[destinationFile],
            ranks[destinationRank]
        ));
    }

    public static Optional<Position> validatePositionBounds(Position currentPosition, KnightDirection direction) {
        File startFile = currentPosition.file();
        Rank startRank = currentPosition.rank();
        File[] files = File.values();
        Rank[] ranks = Rank.values();

        int destinationFile = startFile.ordinal() + direction.getFileCoef();
        int destinationRank = startRank.ordinal() + direction.getRankCoef();
        if (    destinationFile >= files.length || destinationFile < 0
            ||  destinationRank >= ranks.length || destinationRank < 0)
            return null;
        return Optional.of(new Position(
            files[destinationFile],
            ranks[destinationRank]
        ));
    }
}
