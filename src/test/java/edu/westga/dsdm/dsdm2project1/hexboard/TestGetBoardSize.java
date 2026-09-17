package edu.westga.dsdm.dsdm2project1.hexboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.westga.dsdm2project1.Coordinate;
import org.junit.jupiter.api.Test;

import edu.westga.dsdm2project1.Model.HexBoard;

/**
 * Test class for the getBoardSize method of the HexBoard class.
 */
public class TestGetBoardSize {

    @Test
    void testSmallestBoardSize() {
        assertEquals(1, new HexBoard(1).getBoardSize());
    }

    @Test
    void testTypicalBoardSize() {
        assertEquals(6, new HexBoard(6).getBoardSize());
    }

    @Test
    void testSizeUnchangedAfterPlacingQueen() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(0, 0));

        assertEquals(4, board.getBoardSize());
    }
}