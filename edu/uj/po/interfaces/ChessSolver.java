package edu.uj.po.interfaces;

import java.util.Optional;

/**
 * Interfejs programu rozwiązującego zadania szachowe.
 */
class ChessSolver implements Setup, Solver {
    private Board board;
    private PieceBuilder pieceBuilder;
	/**
	 * Bierki podanego koloru zaczynają i dają mata (o ile jest to możliwe) w jednym
	 * ruchu.
	 *
	 * @param color kolor strony wykonującej ruch
	 * @return ruch (o ile istnieje) bierki kończący partię matem.
	 */
	public Optional<Move> findMateInOneMove(Color color) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }

	/**
	 * Bierki podanego koloru zaczynają i dają pata (o ile jest to możliwe) w jednym
	 * ruchu.
	 *
	 * @param color kolor strony wykonującej ruch
	 * @return ruch (o ile istnieje) bierki kończący partię patem.
	 */
	public Optional<Move> findStalemateInOneMove(Color color) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }

    @Override
    public void reset() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'reset'");
    }

    @Override
    public void addChessPiece(Position position, Color color, ChessPiece piece) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'addChessPiece'");
    }
}
