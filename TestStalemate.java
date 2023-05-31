import java.util.Optional;

import edu.uj.po.interfaces.ChessPiece;
import edu.uj.po.interfaces.Color;
import edu.uj.po.interfaces.File;
import edu.uj.po.interfaces.Move;
import edu.uj.po.interfaces.Position;
import edu.uj.po.interfaces.Rank;

public class TestStalemate {

    public static boolean case1() {
        // https://lichess.org/c1CeBiTe#2
        ChessSolver chessSolver = new ChessSolver();

        chessSolver.addChessPiece(new Position(File.c, Rank.FIFTH), Color.BLACK, ChessPiece.QUEEN);
        chessSolver.addChessPiece(new Position(File.b, Rank.SIXTH), Color.BLACK, ChessPiece.KING);
        chessSolver.addChessPiece(new Position(File.a, Rank.EIGHTH), Color.WHITE, ChessPiece.KING);

        Optional<Move> stalemateMove = chessSolver.findStalemateInOneMove(Color.BLACK);
        System.out.println(stalemateMove);
        return stalemateMove.isPresent();
    }

}
