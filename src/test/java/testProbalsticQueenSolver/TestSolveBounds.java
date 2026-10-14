package testProbalsticQueenSolver;

import edu.westga.dsdm2project1.model.Coordinate;
import edu.westga.dsdm2project1.model.ProbabilisticQueenSolver;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ProbabilisticQueenSolver to ensure it adheres to expected bounds.
 */
public class TestSolveBounds {

    private final ProbabilisticQueenSolver solver =
            new ProbabilisticQueenSolver();

    @Test
    void testSizeOneFindsOneQueen() {
        Set<Coordinate> queens = this.solver.solve(1, 10);

        assertEquals(1, queens.size());
    }

    @Test
    void testNeverExceedsPigeonholeBound() {
        for (int size = 1; size <= 8; size++) {
            Set<Coordinate> queens = this.solver.solve(size, 100);

            int upperBound = 2 * size - 1;

            assertTrue(queens.size() <= upperBound,
                    "Exceeded upper bound for size " + size);
        }
    }

    @Test
    void testReturnedSetCannotBeEmpty() {
        for (int size = 1; size <= 5; size++) {
            Set<Coordinate> queens = this.solver.solve(size, 10);

            assertFalse(queens.isEmpty());
        }
    }
}

