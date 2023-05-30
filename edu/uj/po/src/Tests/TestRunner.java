package edu.uj.po.src.Tests;

public class TestRunner {

    public void run() {
        System.out.println("Starting tests...");

        System.out.println("Testing simple move discovery...");
        assertify(SimpleMoveDiscovery.testKingDiscovery(), "King");
        assertify(SimpleMoveDiscovery.testRookDiscovery(), "Rook");
        assertify(SimpleMoveDiscovery.testBishopDiscovery(), "Bishop");
        assertify(SimpleMoveDiscovery.testQueenDiscovery(), "Queen");
        assertify(SimpleMoveDiscovery.testKnightDiscovery(), "Knight");
        assertify(SimpleMoveDiscovery.testPawnDiscoverySimple(), "Pawn (simple)");
        assertify(SimpleMoveDiscovery.testPawnDiscoveryAttack(), "Pawn (attack)");
        System.out.println("Simple move discovery - OK");

        System.out.println("Testing obstructed move discovery...");
        assertify(ObstructedMoveDiscovery.testKingDiscovery(), "King");
        assertify(ObstructedMoveDiscovery.testRookDiscovery(), "Rook");
        assertify(ObstructedMoveDiscovery.testBishopDiscovery(), "Bishop");
        assertify(ObstructedMoveDiscovery.testQueenDiscovery(), "Queen");
        assertify(ObstructedMoveDiscovery.testKnightDiscovery(), "Knight");
        assertify(ObstructedMoveDiscovery.testPawnDiscoverySimple(), "Pawn (simple)");
        assertify(ObstructedMoveDiscovery.testPawnDiscoveryAttack(), "Pawn (attack)");
        System.out.println("Obstructed move discovery - OK");

        System.out.println("Tests finished");
    }

    public static void assertify(boolean thesis, String message) {
        if (thesis) {
            System.out.println(message + " move discovery - OK");
            return;
        } else throw new Error(message + " move discovery failed!");
    }
}
