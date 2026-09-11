package edu.westga.dsdm.dsdm2project1.hexboard;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.dsdm2project1.Coordinate;
import edu.westga.dsdm2project1.Model.HexBoard;

public class TestIsUnderAttack {

    @Test
    void testEmptyBoardHasNoAttacks() {
        assertFalse(new HexBoard(4).isUnderAttack(new Coordinate(0, 0)));
    }

    @Test
    void testSameXIsAttack() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(0, 0));

        assertTrue(board.isUnderAttack(new Coordinate(0, 2)));
    }

    @Test
    void testSameYIsAttack() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(0, 0));

        assertTrue(board.isUnderAttack(new Coordinate(2, 0)));
    }

    @Test
    void testSameZIsAttack() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(0, 0));

        assertTrue(board.isUnderAttack(new Coordinate(2, -2)));
    }

    @Test
    void testEqualXMinusYIsAttack() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(0, 0));

        assertTrue(board.isUnderAttack(new Coordinate(1, 1)));
    }

    @Test
    void testEqualXMinusZIsAttack() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(0, 0));

        assertTrue(board.isUnderAttack(new Coordinate(-1, 2)));
    }

    @Test
    void testEqualYMinusZIsAttack() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(0, 0));

        assertTrue(board.isUnderAttack(new Coordinate(2, -1)));
    }

    @Test
    void testSpecificationExampleIsAttack() {
        HexBoard board = new HexBoard(3);
        board.setQueen(new Coordinate(2, 0));

        assertTrue(board.isUnderAttack(new Coordinate(0, -2)));
    }

    @Test
    void testNonAttackedCell() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(0, 0));

        assertFalse(board.isUnderAttack(new Coordinate(1, -3)));
    }

    @Test
    void testAttackIsSymmetric() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(2, -1));

        assertTrue(board.isUnderAttack(new Coordinate(0, 0)));
    }

    @Test
    void testCellIsSafeAfterQueenRemoved() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(0, 0));
        board.unsetQueen(new Coordinate(0, 0));

        assertFalse(board.isUnderAttack(new Coordinate(0, 2)));
    }

    @Test
    public void testQueenOwnCellIsUnderAttack() {
        HexBoard board = new HexBoard(4);
        board.setQueen(new Coordinate(0, 0));

        assertTrue(board.isUnderAttack(new Coordinate(0, 0)));
    }

    @Test
    void testOffBoardCellIsNotUnderAttack() {
        HexBoard board = new HexBoard(2);
        board.setQueen(new Coordinate(0, 0));

        assertFalse(board.isUnderAttack(new Coordinate(0, 5)));
    }

    @Test
    void testNullIsNotUnderAttack() {
        assertFalse(new HexBoard(4).isUnderAttack(null));
    }
}