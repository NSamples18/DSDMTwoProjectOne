package edu.westga.dsdm.dsdm2project1.hexboard;

import edu.westga.dsdm2project1.Model.HexBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for HexBoard.
 */
public class TestGetBoardCoordinates {
    /**
     * Test getBoardCoordinates method.
     */
    @Test
    void testGetCoordinates() {
        HexBoard board = new HexBoard(3);

        assertEquals(19, board.getBoardCoordinates().size());
    }

    @Test
    void testSizeZeroThrows() {
        assertThrows(IllegalArgumentException.class, () -> new HexBoard(0));
    }

    @Test
    void testNegativeSizeThrows() {
        assertThrows(IllegalArgumentException.class, () -> new HexBoard(-3));
    }

    @Test
    void testSmallestValidBoard() {
        HexBoard board = new HexBoard(1);

        assertAll(() -> assertEquals(1, board.getBoardSize()), () -> assertEquals(0, board.getRadius()), () -> assertEquals(1, board.getBoardCoordinates().size()), () -> assertEquals(0, board.getNumberQueens()));
    }

    @Test
    void testSizeThreeBoard() {
        HexBoard board = new HexBoard(3);

        assertAll(() -> assertEquals(3, board.getBoardSize()), () -> assertEquals(2, board.getRadius()), () -> assertEquals(19, board.getBoardCoordinates().size()), () -> assertEquals(0, board.getNumberQueens()));
    }

    @Test
    void testSizeSixBoardHasNinetyOneCells() {
        HexBoard board = new HexBoard(6);

        assertEquals(91, board.getBoardCoordinates().size());
    }
}
