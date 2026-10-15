package edu.westga.dsdm2project1.Model;

import java.util.*;

/**
 * Probabilistic algorithm for solving the hexagonal N-Queens problem.
 *
 * @author Justice Ricks
 * @version Fall 2026
 */
public class ProbabilisticQueenSolver {
    private final Random random;

    /**
     * Creates a new probabilistic queen solver.
     */
    public ProbabilisticQueenSolver() {
        this.random = new Random();
    }

    /**
     * Runs the probabilistic algorithm k times.
     *
     * @param boardSize   size of the hex board
     * @param repetitions number of attempts (k)
     * @return the best set of non-attacking queens found
     */
    public Set<Coordinate> solve(int boardSize, int repetitions) {

        if (boardSize < 1 || repetitions < 1) {
            throw new IllegalArgumentException(
                    "Board size and repetitions must be positive.");
        }

        HexBoard board = new HexBoard(boardSize);

        List<Coordinate> cells =
                new ArrayList<>(board.getBoardCoordinates());

        Set<Coordinate> solutionSet = new HashSet<>();

        // Pigeonhole principle upper bound
        int target = 2 * boardSize - 1;

        for (int trial = 0; trial < repetitions; trial++) {

            Collections.shuffle(cells, this.random);

            Set<Coordinate> queenSet = new HashSet<>();
            AttackLines occupied = new AttackLines();

            // Random phase: attempt half of all cells
            int randomLimit = Math.max(1, cells.size() / 2);

            for (int i = 0; i < randomLimit; i++) {
                this.tryAdd(cells.get(i), queenSet, occupied);
            }

            // Greedy completion: attempt remaining cells
            for (int i = randomLimit; i < cells.size(); i++) {
                this.tryAdd(cells.get(i), queenSet, occupied);
            }

            if (queenSet.size() == target) {
                return Set.copyOf(queenSet);
            }

            if (queenSet.size() > solutionSet.size()) {
                solutionSet = new HashSet<>(queenSet);
            }
        }

        return Set.copyOf(solutionSet);
    }

    /**
     * Attempts to place a queen on a safe coordinate.
     */
    private void tryAdd(Coordinate cell,
                        Set<Coordinate> queenSet,
                        AttackLines occupied) {

        if (!occupied.isUnderAttack(cell)) {
            queenSet.add(cell);
            occupied.record(cell);
        }
    }

    /**
     * Stores the six hexagonal queen attack relations.
     */
    private static final class AttackLines {

        private final Set<Integer> usedX = new HashSet<>();
        private final Set<Integer> usedY = new HashSet<>();
        private final Set<Integer> usedZ = new HashSet<>();

        private final Set<Integer> usedXY = new HashSet<>();
        private final Set<Integer> usedXZ = new HashSet<>();
        private final Set<Integer> usedYZ = new HashSet<>();

        /**
         * Checks whether any existing queen attacks a cell.
         */
        boolean isUnderAttack(Coordinate cell) {

            int x = cell.x();
            int y = cell.y();
            int z = -(x + y);

            return this.usedX.contains(x)
                    || this.usedY.contains(y)
                    || this.usedZ.contains(z)
                    || this.usedXY.contains(x - y)
                    || this.usedXZ.contains(x - z)
                    || this.usedYZ.contains(y - z);
        }

        /**
         * Records the six attack lines for a queen.
         */
        void record(Coordinate cell) {

            int x = cell.x();
            int y = cell.y();
            int z = -(x + y);

            this.usedX.add(x);
            this.usedY.add(y);
            this.usedZ.add(z);

            this.usedXY.add(x - y);
            this.usedXZ.add(x - z);
            this.usedYZ.add(y - z);
        }
    }
}