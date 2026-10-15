package edu.westga.dsdm2project1.Model;

import java.util.Objects;

/**
 * Coordinate for a hexagonal chessboard.
 *
 * @param x the x-coordinate
 * @param y the y-coordinate
 */
public record Coordinate(int x, int y) {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Coordinate(int x1, int y1))) {
            return false;
        }
        return this.x == x1 && this.y == y1;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}
