package edu.westga.dsdm.dsdm2project1.hexboard;

import edu.westga.dsdm2project1.Model.Coordinate;
import edu.westga.dsdm2project1.Model.HexBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the unsetQueen method of the HexBoard class.
 */
public class TestUnsetQueen {
    @Test
    void testUnsetExistingQueen() {
        HexBoard board = new HexBoard(4);
        Coordinate coordinate = new Coordinate(0, 0);

        board.setQueen(coordinate);

        assertTrue(board.unsetQueen(coordinate));
        assertFalse(board.hasQueen(coordinate));
        assertEquals(0, board.getNumberQueens());
    }

    @Test
    void testUnsetCellWithoutQueenReturnsFalse() {
        HexBoard board = new HexBoard(4);

        assertFalse(board.unsetQueen(new Coordinate(0, 0)));
        assertEquals(0, board.getNumberQueens());
    }

    @Test
    void testCannotUnsetSameQueenTwice() {
        HexBoard board = new HexBoard(4);
        Coordinate coordinate = new Coordinate(0, 0);

        board.setQueen(coordinate);

        assertTrue(board.unsetQueen(coordinate));
        assertFalse(board.unsetQueen(coordinate));
        assertEquals(0, board.getNumberQueens());
    }
}
