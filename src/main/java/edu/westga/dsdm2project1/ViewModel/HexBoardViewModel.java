package edu.westga.dsdm2project1.ViewModel;

import edu.westga.dsdm2project1.Coordinate;
import edu.westga.dsdm2project1.Model.HexBoard;

import java.util.Collection;

/**
 * ViewModel for the HexBoard.
 */
public class HexBoardViewModel {
    private HexBoard board;

    /**
     * Creates a new HexBoardViewModel with the given size.
     *
     * @param size the size of the hex board
     */
    public HexBoardViewModel(int size) {
        this.board = new HexBoard(size);
    }

    /**
     * Creates a new HexBoardViewModel with the default size of 3.
     *
     * @param size the size of the hex board
     */
    public void createBoard(int size) {
        this.board = new HexBoard(size);
    }

    /**
     * Gets the coordinates on the board.
     *
     * @return the board coordinates
     */
    public Collection<Coordinate> getBoardCoordinates() {
        return this.board.getBoardCoordinates();
    }

    /**
     * Gets the number of queens.
     *
     * @return the number of queens
     */
    public int getNumberQueens() {
        return this.board.getNumberQueens();
    }

    /**
     * Determines whether a coordinate has a queen.
     *
     * @param coordinate the coordinate
     * @return true if the coordinate has a queen
     */
    public boolean hasQueen(Coordinate coordinate) {
        return this.board.hasQueen(coordinate);
    }

    /**
     * Determines whether a coordinate is under attack.
     *
     * @param coordinate the coordinate
     * @return true if the coordinate is under attack
     */
    public boolean isUnderAttack(Coordinate coordinate) {
        return this.board.isUnderAttack(coordinate);
    }

    /**
     * Handles the selection of a board cell.
     *
     * @param coordinate the selected coordinate
     * @return true if the board was changed
     */
    public boolean selectCell(Coordinate coordinate) {
        if (this.board.hasQueen(coordinate)) {
            return this.board.unsetQueen(coordinate);
        }

        return this.board.setQueen(coordinate);
    }
}
