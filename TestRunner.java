public class TestRunner {

    public void run() {
        System.out.println("Starting tests...");

        System.out.println("Testing simple move discovery...");
        assertify(TestSimpleDiscovery.testKingDiscovery(), "King");
        assertify(TestSimpleDiscovery.testRookDiscovery(), "Rook");
        assertify(TestSimpleDiscovery.testBishopDiscovery(), "Bishop");
        assertify(TestSimpleDiscovery.testQueenDiscovery(), "Queen");
        assertify(TestSimpleDiscovery.testKnightDiscovery(), "Knight");
        assertify(TestSimpleDiscovery.testPawnDiscoverySimple(), "Pawn (simple)");
        assertify(TestSimpleDiscovery.testPawnDiscoveryAttack(), "Pawn (attack)");
        System.out.println("Simple move discovery - OK");

        System.out.println("Testing obstructed move discovery...");
        assertify(TestObstructedDiscovery.testKingDiscovery(), "King");
        assertify(TestObstructedDiscovery.testRookDiscovery(), "Rook");
        assertify(TestObstructedDiscovery.testBishopDiscovery(), "Bishop");
        assertify(TestObstructedDiscovery.testQueenDiscovery(), "Queen");
        assertify(TestObstructedDiscovery.testKnightDiscovery(), "Knight");
        assertify(TestObstructedDiscovery.testPawnDiscoverySimple(), "Pawn (simple)");
        assertify(TestObstructedDiscovery.testPawnDiscoveryAttack(), "Pawn (attack)");
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
