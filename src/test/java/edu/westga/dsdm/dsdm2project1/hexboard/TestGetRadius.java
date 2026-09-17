package edu.westga.dsdm.dsdm2project1.hexboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.dsdm2project1.Model.HexBoard;

/**
 * Test class for the getRadius method of the HexBoard class.
 */
public class TestGetRadius {

    @Test
    void testRadiusOfSmallestBoard() {
        assertEquals(0, new HexBoard(1).getRadius());
    }

    @Test
    void testRadiusIsOneLessThanSize() {
        assertEquals(1, new HexBoard(2).getRadius());
    }

    @Test
    void testRadiusOfLargerBoard() {
        assertEquals(5, new HexBoard(6).getRadius());
    }
}