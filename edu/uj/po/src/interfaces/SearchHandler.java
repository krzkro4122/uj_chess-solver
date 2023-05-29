package edu.uj.po.src.interfaces;

import java.util.Optional;

import edu.uj.po.interfaces.Move;

public interface SearchHandler {
    public Optional<Move> findMate();
    public Optional<Move> findStaleMate();
}
