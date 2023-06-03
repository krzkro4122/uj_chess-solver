public class TestRunner {

    public void run() {
        System.out.println("Starting tests...");

        System.out.println("Testing simple move discovery...");
        assertify(TestSimpleDiscovery.testKingDiscovery(), "King");
        assertify(TestSimpleDiscovery.testRookDiscovery(), "Rook");
        assertify(TestSimpleDiscovery.testBishopDiscovery(), "Bishop");
        assertify(TestSimpleDiscovery.testQueenDiscovery(), "Queen");
        assertify(TestSimpleDiscovery.testKnightDiscovery(), "Knight");
        assertify(TestSimpleDiscovery.testPawnDiscoverySimple1(), "Pawn (simple1)");
        assertify(TestSimpleDiscovery.testPawnDiscoverySimple2(), "Pawn (simple2)");
        assertify(TestSimpleDiscovery.testPawnDiscoveryAttack(), "Pawn (attack)");
        System.out.println("Simple move discovery - OK");

        System.out.println("Testing obstructed move discovery...");
        assertify(TestObstructedDiscovery.testKingDiscovery(), "King");
        assertify(TestObstructedDiscovery.testRookDiscovery(), "Rook");
        assertify(TestObstructedDiscovery.testBishopDiscovery(), "Bishop");
        assertify(TestObstructedDiscovery.testQueenDiscovery(), "Queen");
        assertify(TestObstructedDiscovery.testKnightDiscovery(), "Knight");
        assertify(TestObstructedDiscovery.testPawnDiscoverySimple1(), "Pawn (simple1)");
        assertify(TestObstructedDiscovery.testPawnDiscoverySimple2(), "Pawn (simple2)");
        assertify(TestObstructedDiscovery.testPawnDiscoveryAttack(), "Pawn (attack)");
        System.out.println("Obstructed move discovery - OK");

        System.out.println("Testing mate search...");
        assertify(TestMate.case1(), "Case 1");
        assertify(TestMate.case2(), "Case 2");
        assertify(TestMate.promotion(), "Promotion");
        assertify(TestMate.kingDefense(), "King defense");
        System.out.println("Mate search - OK");

        System.out.println("Testing stalemate search...");
        assertify(TestStalemate.case1(), "Case 1");
        System.out.println("Stalemate search - OK");

        System.out.println("Tests finished");
    }

    public static void assertify(boolean condition, String message) {
        if (condition) {
            System.out.println(message + " OK");
            return;
        } else throw new Error(message + " failed!");
    }
}
