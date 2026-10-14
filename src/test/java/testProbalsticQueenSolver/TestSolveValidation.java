package testProbalsticQueenSolver;

import edu.westga.dsdm2project1.model.ProbabilisticQueenSolver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ProbabilisticQueenSolver to validate input parameters.
 */
public class TestSolveValidation {

    private final ProbabilisticQueenSolver solver =
            new ProbabilisticQueenSolver();

    @Test
    void testZeroBoardSizeThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> this.solver.solve(0, 100));
    }

    @Test
    void testNegativeBoardSizeThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> this.solver.solve(-3, 100));
    }

    @Test
    void testZeroRepetitionsThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> this.solver.solve(3, 0));
    }

    @Test
    void testNegativeRepetitionsThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> this.solver.solve(3, -5));
    }

    @Test
    void testValidInputsDoNotThrowException() {
        assertDoesNotThrow(() -> this.solver.solve(3, 100));
    }
}

