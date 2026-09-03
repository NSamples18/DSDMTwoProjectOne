package edu.westga.dsdm2project1.Model;

import edu.westga.dsdm2project1.Coordinate;

import java.util.Collection;
import java.util.List;

public class HexBoard implements QueensHexBoard {
    private final int boardsize;
    private final int raduis;
    private final Collection<Coordinate> queens;
    private final  Collection<Coordinate> boardCoordinates;

    public HexBoard(int boardsize, int raduis, Collection<Coordinate> queens, Collection<Coordinate> boardCoordinates) {
        this.boardsize = boardsize;
        this.raduis = raduis;
        this.queens = queens;
        this.boardCoordinates = boardCoordinates;
    }

    @Override
    public int getRadius() {
        return this.raduis;
    }

    @Override
    public int getBoardSize() {
        return this.boardsize;
    }

    @Override
    public int getNumberQueens() {
        return this.queens.size();
    }

    @Override
    public Collection<Coordinate> getBoardCoordinates() {
        return List.of();
    }

    @Override
    public Collection<Coordinate> getQueens() {
        return List.of();
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
