package edu.westga.dsdm.dsdm2project1.hexboard;

import edu.westga.dsdm2project1.Model.Coordinate;
import edu.westga.dsdm2project1.Model.HexBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the setQueen method of the HexBoard class.
 */
public class TestSetQueen {
    @Test
    void testSetQueenOnEmptySafeCell() {
        HexBoard board = new HexBoard(4);
        Coordinate coordinate = new Coordinate(0, 0);

        assertTrue(board.setQueen(coordinate));
        assertTrue(board.hasQueen(coordinate));
        assertEquals(1, board.getNumberQueens());
    }

    @Test
    void testCannotSetQueenOnCellThatAlreadyHasQueen() {
        HexBoard board = new HexBoard(4);
        Coordinate coordinate = new Coordinate(0, 0);

        board.setQueen(coordinate);

        assertFalse(board.setQueen(coordinate));
        assertEquals(1, board.getNumberQueens());
    }

    @Test
    void testCannotSetQueenOnAttackedCell() {
        HexBoard board = new HexBoard(4);

        board.setQueen(new Coordinate(0, 0));

        Coordinate attackedCoordinate = new Coordinate(0, 2);

        assertFalse(board.setQueen(attackedCoordinate));
        assertFalse(board.hasQueen(attackedCoordinate));
        assertEquals(1, board.getNumberQueens());
    }

    @Test
    void testCanSetQueenOnNonAttackedCell() {
        HexBoard board = new HexBoard(4);

        board.setQueen(new Coordinate(0, 0));

        Coordinate safeCoordinate = new Coordinate(1, -3);

        assertTrue(board.setQueen(safeCoordinate));
        assertTrue(board.hasQueen(safeCoordinate));
        assertEquals(2, board.getNumberQueens());
    }
}
