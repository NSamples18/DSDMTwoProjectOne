package testProbalsticQueenSolver;

import edu.westga.dsdm2project1.Model.ProbabilisticQueenSolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ProbabilisticQueenSolver constructor.
 */
public class TestSolverConstructor {

    @Test
    void testConstructorCreatesSolver() {
        ProbabilisticQueenSolver solver =
                new ProbabilisticQueenSolver();

        assertNotNull(solver);
    }

    @Test
    void testNewSolverCanFindSolution() {
        ProbabilisticQueenSolver solver =
                new ProbabilisticQueenSolver();

        assertFalse(solver.solve(3, 100).isEmpty());
    }
}

