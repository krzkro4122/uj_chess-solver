package edu.uj.po.src;

import java.util.List;

import edu.uj.po.interfaces.ChessPiece;
import edu.uj.po.interfaces.ChessSolver;
import edu.uj.po.interfaces.Color;
import edu.uj.po.interfaces.File;
import edu.uj.po.interfaces.Move;
import edu.uj.po.interfaces.Position;
import edu.uj.po.interfaces.Rank;

public class Main {

    public static void main(String[] args) {
        System.out.println("Starting tests...");
        ChessSolver chessSolver = new ChessSolver();
        System.out.println("Testing move discovery...");
        assertify(testMoveDiscovery(chessSolver), "Move discovery failed!");
        System.out.println("Tests finished");
    }

    private static boolean testMoveDiscovery(ChessSolver chessSolver) {
        chessSolver.addChessPiece(new Position(File.d, Rank.FIFTH), Color.WHITE, ChessPiece.BISHOP);
        List<Move> moveList = chessSolver.returnify();
        System.out.println(moveList);
        if (moveList.isEmpty()) {
            return false;
        }
        return true;
    }

    private static void assertify(boolean thesis, String message) {
        if (thesis) {
            return;
        } else throw new Error(message);
    }
}
