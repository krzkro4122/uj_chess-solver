package edu.uj.po.interfaces;

import java.util.Optional;

public interface SearchHandler {
    public Optional<Move> findMate();
    public Optional<Move> findStaleMate();
}
