package edu.westga.dsdm.dsdm2project1.hexboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.dsdm2project1.Model.Coordinate;
import edu.westga.dsdm2project1.Model.HexBoard;

/**
 * Test class for the getNumberQueens method of the HexBoard class.
 */
public class TestGetNumberQueens {

    @Test
    void testNewBoardHasNoQueens() {
        assertEquals(0, new HexBoard(4).getNumberQueens());
    }

    @Test
    void testCountAfterOneQueen() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(0, 0));

        assertEquals(1, board.getNumberQueens());
    }

    @Test
    void testCountAfterTwoNonAttackingQueens() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(0, 0));
        board.setQueen(new Coordinate(1, -3));

        assertEquals(2, board.getNumberQueens());
    }

    @Test
    void testRejectedQueenDoesNotChangeCount() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(0, 0));
        board.setQueen(new Coordinate(0, 1));

        assertEquals(1, board.getNumberQueens());
    }

    @Test
    void testCountAfterRemovingQueen() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(0, 0));
        board.unsetQueen(new Coordinate(0, 0));

        assertEquals(0, board.getNumberQueens());
    }
}