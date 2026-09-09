package edu.westga.dsdm.dsdm2project1.hexboard;

import edu.westga.dsdm2project1.Model.HexBoard;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}