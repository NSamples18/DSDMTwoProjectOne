package edu.westga.dsdm2project1.Model;

import edu.westga.dsdm2project1.Coordinate;

import java.util.Collection;
import java.util.List;

/**
 * Hexboard class implements the QueensHexBoard Interface
 * this class is designed to be the businness logic class behind our board
 * @author Justice Ricks
 * @version fall 2026
 */
public class HexBoard implements QueensHexBoard {
    private final int boardSize;
    private final int radius;
    private final Collection<Coordinate> queens;
    private final  Collection<Coordinate> boardCoordinates;

    public HexBoard(int boardSize, int radius, Collection<Coordinate> queens, Collection<Coordinate> boardCoordinates) {
        this.boardSize = boardSize;
        this.radius = radius;
        this.queens = queens;
        this.boardCoordinates = boardCoordinates;
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
        return false;
    }

    @Override
    public boolean setQueen(Coordinate coordinate) {
        return false;
    }

    @Override
    public boolean unsetQueen(Coordinate coordinate) {
        return false;
    }
}
