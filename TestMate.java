import java.util.Optional;

import edu.uj.po.interfaces.ChessPiece;
import edu.uj.po.interfaces.Color;
import edu.uj.po.interfaces.File;
import edu.uj.po.interfaces.Move;
import edu.uj.po.interfaces.Position;
import edu.uj.po.interfaces.Rank;

public class TestMate {

    public static boolean case1() {
        // https://lichess.org/97Cxaqvr#2
        ChessSolver chessSolver = new ChessSolver();

        chessSolver.addChessPiece(new Position(File.h, Rank.FOURTH), Color.BLACK, ChessPiece.KNIGHT);
        chessSolver.addChessPiece(new Position(File.f, Rank.SECOND), Color.BLACK, ChessPiece.KING);
        chessSolver.addChessPiece(new Position(File.g, Rank.SECOND), Color.BLACK, ChessPiece.BISHOP);
        chessSolver.addChessPiece(new Position(File.h, Rank.SECOND), Color.WHITE, ChessPiece.KING);

        Optional<Move> mateMove = chessSolver.findMateInOneMove(Color.BLACK);
        System.out.println(mateMove);
        return mateMove.isPresent();
    }

    public static boolean case2() {
        // https://lichess.org/UdVfaoy0
        ChessSolver chessSolver = new ChessSolver();

        chessSolver.addChessPiece(new Position(File.e, Rank.SIXTH), Color.BLACK, ChessPiece.QUEEN);
        chessSolver.addChessPiece(new Position(File.b, Rank.SIXTH), Color.BLACK, ChessPiece.KING);
        chessSolver.addChessPiece(new Position(File.h, Rank.THIRD), Color.WHITE, ChessPiece.PAWN);
        chessSolver.addChessPiece(new Position(File.a, Rank.EIGHTH), Color.WHITE, ChessPiece.KING);

        Optional<Move> mateMove = chessSolver.findMateInOneMove(Color.BLACK);
        System.out.println(mateMove);
        return mateMove.isPresent();
    }

    public static boolean promotion() {
        // https://lichess.org/
        ChessSolver chessSolver = new ChessSolver();

        chessSolver.addChessPiece(new Position(File.d, Rank.SECOND), Color.BLACK, ChessPiece.PAWN);
        chessSolver.addChessPiece(new Position(File.a, Rank.EIGHTH), Color.BLACK, ChessPiece.KING);
        chessSolver.addChessPiece(new Position(File.b, Rank.SECOND), Color.WHITE, ChessPiece.KING);

        chessSolver.addChessPiece(new Position(File.a, Rank.FIFTH), Color.BLACK, ChessPiece.KING);
        chessSolver.addChessPiece(new Position(File.c, Rank.FIFTH), Color.BLACK, ChessPiece.KING);
        chessSolver.addChessPiece(new Position(File.g, Rank.FOURTH), Color.BLACK, ChessPiece.KING);
        chessSolver.addChessPiece(new Position(File.g, Rank.SECOND), Color.BLACK, ChessPiece.KING);

        Optional<Move> mateMove = chessSolver.findMateInOneMove(Color.BLACK);
        System.out.println(mateMove);
        return mateMove.isPresent();
    }
}
