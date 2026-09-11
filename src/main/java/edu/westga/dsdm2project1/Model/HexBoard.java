package edu.westga.dsdm2project1.Model;

import edu.westga.dsdm2project1.Coordinate;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * HexBoard class implements the QueensHexBoard Interface.
 * this class is designed to be the business logic class behind our board.
 *
 * @author Justice Ricks
 * @version fall 2026
 */
public class HexBoard implements QueensHexBoard {
    private final int boardSize;
    private final int radius;
    private final Collection<Coordinate> queens;
    private final Collection<Coordinate> boardCoordinates;

    /**
     * Constructs a HexBoard with the specified size.
     *
     * @param boardSize the size of the hexagonal board
     * @throws IllegalArgumentException if the board size is less than 1
     */
    public HexBoard(int boardSize) {
        if (boardSize < 1) {
            throw new IllegalArgumentException("Board size must be at least 1.");
        }

        this.boardSize = boardSize;
        this.radius = boardSize - 1;
        this.queens = new HashSet<>();
        this.boardCoordinates = new HashSet<>();
        this.generateBoardCoordinates();
    }

    @Override
    public int getRadius() {
        return this.radius;
    }

    @Override
    public int getBoardSize() {
        return this.boardSize;
    }

    @Override
    public int getNumberQueens() {
        return this.queens.size();
    }

    @Override
    public Collection<Coordinate> getBoardCoordinates() {
        return List.copyOf(this.boardCoordinates);
    }

    @Override
    public Collection<Coordinate> getQueens() {
        return List.copyOf(this.queens);
    }

    @Override
    public boolean hasQueen(Coordinate coordinate) {
        return this.queens.contains(coordinate);
    }

    @Override
    public boolean isUnderAttack(Coordinate coordinate) {
        if (coordinate == null || !this.boardCoordinates.contains(coordinate)) {
            return false;
        }

        for (Coordinate queen : this.queens) {
            if (this.isSameHexLine(queen, coordinate)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public boolean setQueen(Coordinate coordinate) {
        if (coordinate == null || !this.boardCoordinates.contains(coordinate) || this.hasQueen(coordinate)) {
            return false;
        }
        if (this.isUnderAttack(coordinate)) {
            return false;
        }

        return this.queens.add(coordinate);
    }

    @Override
    public boolean unsetQueen(Coordinate coordinate) {
        if (coordinate == null) {
            return false;
        }
        return this.queens.remove(coordinate);
    }

    private void generateBoardCoordinates() {
        for (int x = -this.radius; x <= this.radius; x++) {
            for (int y = -this.radius; y <= this.radius; y++) {
                int z = -(x + y);

                if (Math.abs(z) <= this.radius) {
                    this.boardCoordinates.add(new Coordinate(x, y));
                }
            }
        }
    }

    private boolean isSameHexLine(Coordinate first, Coordinate second) {
        int firstZ = -(first.x() + first.y());
        int secondZ = -(second.x() + second.y());

        return first.x() == second.x()
                || first.y() == second.y()
                || firstZ == secondZ
                || first.x() - first.y() == second.x() - second.y()
                || first.x() - firstZ == second.x() - secondZ
                || first.y() - firstZ == second.y() - secondZ;
    }
}
