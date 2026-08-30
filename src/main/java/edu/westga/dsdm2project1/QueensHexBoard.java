package edu.westga.dsdm2project1;

import java.util.Collection;

/**
 * A HexBoard represents a hexagonal board with any number queens on it.
 * No two queens may attack each other. The size of the hexagonal board
 * is the number of cells that border a side of the hexagon. The radius
 * is one less than the number of cells on the line from the center to one
 * of the edges. For example, a hex board of size 3 has a radius of 2 and
 * contains 19 cells. A hex board of size 4 has a radius of 3 and contains
 * 37 cells. The center cell on the board has (x,y)-coordinate (0, 0).
 * An implementation of the interface must have a constructor that takes
 * a single integer parameter for the size of the board and creates an
 * object with the specified size and no queens. The constructor must throw
 * an IllegalArgumentException if the size is less than 1.
 */
public interface QueensHexBoard {
    /**
     * Returns the radius of the hexagonal board.
     *
     * @return the radius
     * @pre none
     * @post none
     */
    int getRadius();

    /**
     * Returns the size of this hex board.
     *
     * @return the size
     * @pre none
     * @post none
     */
    int getBoardSize();

    /**
     * Returns the number of queens on this board.
     *
     * @return the number of queens
     * @pre none
     * @post none
     */
    int getNumberQueens();

    /**
     * Returns the coordinates of all cells on this board.
     *
     * @return the coordinates of all cells
     * @pre none
     * @post none
     */
    Collection<Coordinate> getBoardCoordinates();

    /**
     * Returns the coordinates of all queens on this board.
     *
     * @return the coordinates of all queens
     * @pre none
     * @post none
     */
    Collection<Coordinate> getQueens();

    /**
     * Determines if a queen is located on the cell with the specified coordinate.
     *
     * @param coordinate the coordinate of the cell to check
     * @return true if a queen is located on the cell, false otherwise
     * @pre getBoardCoordinates.contains(coordinate)
     * @post none
     */
    boolean hasQueen(Coordinate coordinate);

    /**
     * Determines if the cell with the specified coordinate is under attack by any queen.
     * The cell is also considered to be under attack if a queen is located on that cell.
     *
     * @param coordinate the coordinate of the cell to check
     * @return true if the cell is under attack, false otherwise
     * @pre getBoardCoordinates.contains(coordinate)
     * @post none
     */
    boolean isUnderAttack(Coordinate coordinate);

    /**
     * Places a queen on the cell with the specified coordinate unless the cell
     * is under attack.
     *
     * @param coordinate the coordinate of the cell to place a queen at
     * @return true if the cell with the coordinate was not under attack and an
     * additional queen has been placed; false if the cell is under attack by a
     * queen on another cell or contains a queen
     * @pre getBoardCoordinates.contains(coordinate)
     * @post hasQueen(coordinate) == !isUnderAttack(coordinate)@pre || hasQueen()@pre
     */
    boolean setQueen(Coordinate coordinate);

    /**
     * Removes the queen from the cell with the specified coordinate.
     *
     * @param coordinate the coordinate of the cell from which to remove the queen
     * @return true if a queen was removed from the cell, false if the cell did not contain a queen
     * @pre getBoardCoordinates.contains(coordinate)
     * @post !hasQueen(coordinate)
     */
    boolean unsetQueen(Coordinate coordinate);
}
