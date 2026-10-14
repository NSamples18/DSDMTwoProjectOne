package testProbalsticQueenSolver;

import edu.westga.dsdm2project1.model.Coordinate;
import edu.westga.dsdm2project1.model.HexBoard;
import edu.westga.dsdm2project1.model.ProbabilisticQueenSolver;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the ProbabilisticQueenSolver to ensure that all queens are placed safely on the board.
 */
public class TestSolveSafety {

    private final ProbabilisticQueenSolver solver =
            new ProbabilisticQueenSolver();

    private void assertAllQueensAreSafe(int boardSize) {
        Set<Coordinate> queens =
                this.solver.solve(boardSize, 100);

        HexBoard board = new HexBoard(boardSize);

        for (Coordinate queen : queens) {
            assertTrue(board.getBoardCoordinates().contains(queen),
                    "Queen is outside the board: " + queen);

            assertTrue(board.setQueen(queen),
                    "Queen conflicts with another: " + queen);
        }

        assertEquals(queens.size(), board.getNumberQueens());
    }

    @Test
    void testAllQueensSafeOnSizeTwo() {
        this.assertAllQueensAreSafe(2);
    }

    @Test
    void testAllQueensSafeOnSizeThree() {
        this.assertAllQueensAreSafe(3);
    }

    @Test
    void testAllQueensSafeOnSizeFour() {
        this.assertAllQueensAreSafe(4);
    }

    @Test
    void testAllQueensSafeOnSizeFive() {
        this.assertAllQueensAreSafe(5);
    }

    @Test
    void testAllQueensSafeOnSizeSix() {
        this.assertAllQueensAreSafe(6);
    }
}
