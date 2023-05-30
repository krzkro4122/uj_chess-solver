package edu.uj.po.src.Tests;

import edu.uj.po.interfaces.ChessSolver;

public class TestRunner {

    public void run() {
        System.out.println("Starting tests...");
        System.out.println("Testing move discovery...");
        assertify(SimpleMoveDiscovery.testKingDiscovery(), "King");
        assertify(SimpleMoveDiscovery.testRookDiscovery(), "Rook");
        assertify(SimpleMoveDiscovery.testBishopDiscovery(), "Bishop");
        assertify(SimpleMoveDiscovery.testQueenDiscovery(), "Queen");
        assertify(SimpleMoveDiscovery.testKnightDiscovery(), "Knight");
        assertify(SimpleMoveDiscovery.testPawnDiscoverySimple(), "Pawn (simple)");
        assertify(SimpleMoveDiscovery.testPawnDiscoveryAttack(), "Pawn (attack)");
        System.out.println("Tests finished");
    }

    public static void assertify(boolean thesis, String message) {
        if (thesis) {
            System.out.println(message + " move discovery - OK");
            return;
        } else throw new Error(message + " move discovery failed!");
    }
}
