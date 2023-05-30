package edu.uj.po.src.Tests;

import edu.uj.po.interfaces.ChessSolver;

public class TestRunner {

    public void run() {
        System.out.println("Starting tests...");
        System.out.println("Testing move discovery...");
        assertify(MoveDiscovery.testKingDiscovery(), "King");
        assertify(MoveDiscovery.testRookDiscovery(), "Rook");
        assertify(MoveDiscovery.testBishopDiscovery(), "Bishop");
        assertify(MoveDiscovery.testQueenDiscovery(), "Queen");
        assertify(MoveDiscovery.testKnightDiscovery(), "Knight");
        assertify(MoveDiscovery.testPawnDiscoverySimple(), "Pawn (simple)");
        assertify(MoveDiscovery.testPawnDiscoveryAttack(), "Pawn (attack)");
        System.out.println("Tests finished");
    }

    public static void assertify(boolean thesis, String message) {
        if (thesis) {
            System.out.println(message + " move discovery - OK");
            return;
        } else throw new Error(message + " move discovery failed!");
    }
}
