package edu.westga.dsdm.dsdm2project1.hexboard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;

import edu.westga.dsdm2project1.Coordinate;
import edu.westga.dsdm2project1.Model.HexBoard;

public class TestGetQueens {

    @Test
    void testNewBoardHasNoQueens() {
        assertTrue(new HexBoard(4).getQueens().isEmpty());
    }

    @Test
    void testContainsPlacedQueen() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(0, 0));

        Collection<Coordinate> queens = board.getQueens();

        assertEquals(1, queens.size());
        assertTrue(queens.contains(new Coordinate(0, 0)));
    }

    @Test
    void testDoesNotContainRemovedQueen() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(0, 0));
        board.unsetQueen(new Coordinate(0, 0));

        assertFalse(board.getQueens().contains(new Coordinate(0, 0)));
    }

    @Test
    void testReturnedCollectionIsUnmodifiable() {
        Collection<Coordinate> queens = new HexBoard(4).getQueens();

        assertThrows(UnsupportedOperationException.class, () -> queens.add(new Coordinate(0, 0)));
    }
}