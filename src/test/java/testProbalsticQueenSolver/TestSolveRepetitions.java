package testProbalsticQueenSolver;

import edu.westga.dsdm2project1.Model.Coordinate;
import edu.westga.dsdm2project1.Model.HexBoard;
import edu.westga.dsdm2project1.Model.ProbabilisticQueenSolver;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ProbabilisticQueenSolver to ensure it can find valid solutions
 * with varying numbers of repetitions.
 */
public class TestSolveRepetitions {

    private final ProbabilisticQueenSolver solver =
            new ProbabilisticQueenSolver();

    private void assertValidSolution(int repetitions) {
        Set<Coordinate> queens = this.solver.solve(4, repetitions);
        HexBoard board = new HexBoard(4);

        assertFalse(queens.isEmpty());

        for (Coordinate queen : queens) {
            assertTrue(board.setQueen(queen),
                    "Invalid queen: " + queen);
        }
    }

    @Test
    void testOneRepetition() {
        this.assertValidSolution(1);
    }

    @Test
    void testTenRepetitions() {
        this.assertValidSolution(10);
    }

    @Test
    void testOneHundredRepetitions() {
        this.assertValidSolution(100);
    }

    @Test
    void testFiveHundredRepetitions() {
        this.assertValidSolution(500);
    }
}
