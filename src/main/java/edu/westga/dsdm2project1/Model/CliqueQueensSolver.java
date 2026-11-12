package edu.westga.dsdm2project1.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.jgrapht.Graph;
import org.jgrapht.alg.clique.BronKerboschCliqueFinder;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

/**
 * Finds a starter solution for Hex N-Queens by solving CLIQUE
 * with the Bron-Kerbosch algorithm.
 *
 * Each board cell is a vertex. Two vertices are connected when queens
 * on those cells would NOT attack each other. A clique in this graph is
 * a set of cells that are all safe from each other, so the largest
 * clique found is the queen placement.
 */
public class CliqueQueensSolver {

    private final int radius;
    private final long timeLimitSeconds;
    private boolean timeLimitReached;

    /**
     * Creates a solver for a hex board of the given size.
     *
     * @param size             the board size (number of cells along one side), at least 1
     * @param timeLimitSeconds the time limit in seconds, at least 1
     * @throws IllegalArgumentException if size or timeLimitSeconds is less than 1
     */
    public CliqueQueensSolver(int size, long timeLimitSeconds) {
        if (size < 1) {
            throw new IllegalArgumentException("Board size must be at least 1.");
        }
        if (timeLimitSeconds < 1) {
            throw new IllegalArgumentException("Time limit must be at least 1 second.");
        }
        this.radius = size - 1;
        this.timeLimitSeconds = timeLimitSeconds;
        this.timeLimitReached = false;
    }

    /**
     * Runs Bron-Kerbosch and returns the cells of the largest clique found.
     * If the time limit runs out, returns the largest clique found so far.
     *
     * @return the cells to place queens on
     */
    public List<Coordinate> solve() {
        Graph<Coordinate, DefaultEdge> graph = this.buildNonAttackGraph();

        BronKerboschCliqueFinder<Coordinate, DefaultEdge> finder =
                new BronKerboschCliqueFinder<>(graph, this.timeLimitSeconds, TimeUnit.SECONDS);

        Iterator<Set<Coordinate>> largestCliques = finder.maximumIterator();
        this.timeLimitReached = finder.isTimeLimitReached();

        if (!largestCliques.hasNext()) {
            return Collections.emptyList();
        }
        return new ArrayList<>(largestCliques.next());
    }

    /**
     * Tells whether the last call to solve() was stopped by the time limit.
     * If true, the solution may not be the best possible one.
     *
     * @return true if the time limit was reached
     */
    public boolean isTimeLimitReached() {
        return this.timeLimitReached;
    }

    private Graph<Coordinate, DefaultEdge> buildNonAttackGraph() {
        Graph<Coordinate, DefaultEdge> graph = new SimpleGraph<>(DefaultEdge.class);
        List<Coordinate> cells = this.getAllCells();

        for (Coordinate cell : cells) {
            graph.addVertex(cell);
        }

        for (int first = 0; first < cells.size(); first++) {
            for (int second = first + 1; second < cells.size(); second++) {
                Coordinate cellA = cells.get(first);
                Coordinate cellB = cells.get(second);
                if (!attacks(cellA, cellB)) {
                    graph.addEdge(cellA, cellB);
                }
            }
        }
        return graph;
    }

    private List<Coordinate> getAllCells() {
        List<Coordinate> cells = new ArrayList<>();
        for (int x = -this.radius; x <= this.radius; x++) {
            int minY = Math.max(-this.radius, -x - this.radius);
            int maxY = Math.min(this.radius, -x + this.radius);
            for (int y = minY; y <= maxY; y++) {
                cells.add(new Coordinate(x, y));
            }
        }
        return cells;
    }

    private static boolean attacks(Coordinate cellA, Coordinate cellB) {
        int x1 = cellA.x();
        int y1 = cellA.y();
        int z1 = -x1 - y1;

        int x2 = cellB.x();
        int y2 = cellB.y();
        int z2 = -x2 - y2;

        return x1 == x2
                || y1 == y2
                || z1 == z2
                || x1 - y1 == x2 - y2
                || x1 - z1 == x2 - z2
                || y1 - z1 == y2 - z2;
    }
}
