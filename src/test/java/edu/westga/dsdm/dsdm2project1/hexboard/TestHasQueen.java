package edu.westga.dsdm.dsdm2project1.hexboard;

import edu.westga.dsdm2project1.Model.Coordinate;
import edu.westga.dsdm2project1.Model.HexBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test class for the hasQueen method of the HexBoard class.
 */
public class TestHasQueen {
    @Test
    void testNewBoardDoesNotHaveQueen() {
        HexBoard board = new HexBoard(4);

        assertFalse(board.hasQueen(new Coordinate(0, 0)));
    }

    @Test
    void testHasQueenAfterQueenIsPlaced() {
        HexBoard board = new HexBoard(4);
        Coordinate coordinate = new Coordinate(0, 0);

        board.setQueen(coordinate);

        assertTrue(board.hasQueen(coordinate));
    }

    @Test
    void testDoesNotHaveQueenAfterQueenIsRemoved() {
        HexBoard board = new HexBoard(4);
        Coordinate coordinate = new Coordinate(0, 0);

        board.setQueen(coordinate);
        board.unsetQueen(coordinate);

        assertFalse(board.hasQueen(coordinate));
    }
}
