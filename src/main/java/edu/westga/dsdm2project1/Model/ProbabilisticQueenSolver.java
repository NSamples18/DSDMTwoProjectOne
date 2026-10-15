package edu.westga.dsdm2project1.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Finds non-attacking queens using random trials
 * followed by a greedy completion algorithm.
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
        this(new Random());
    }

    /**
     * Creates a solver with a supplied random generator.
     * Useful for repeatable JUnit tests.
     *
     * @param random the random number generator
     */
    public ProbabilisticQueenSolver(Random random) {
        if (random == null) {
            throw new IllegalArgumentException(
                    "Random generator cannot be null.");
        }
        this.random = random;
    }

    /**
     * Runs the probabilistic algorithm k times.
     *
     * @param boardSize   size of the hex board
     * @param repetitions number of attempts (k)
     * @return the best non-attacking queen set found
     */
    public Set<Coordinate> solve(int boardSize, int repetitions) {
        if (boardSize < 1 || repetitions < 1) {
            throw new IllegalArgumentException(
                    "Board size and repetitions must be positive.");
        }

        HexBoard board = new HexBoard(boardSize);
        List<Coordinate> allCells =
                new ArrayList<>(board.getBoardCoordinates());
        Set<Coordinate> solutionSet = new HashSet<>();
        int target = 2 * boardSize - 1;

        for (int trial = 0; trial < repetitions; trial++) {
            List<Coordinate> remaining = new ArrayList<>(allCells);
            Set<Coordinate> queenSet = new HashSet<>();
            AttackLines occupied = new AttackLines();

            // Phase 1: Attempt a random sample of distinct coordinates.
            int randomAttempts = Math.max(1, allCells.size() / 3);
            this.placeRandomQueens(
                    remaining, queenSet, occupied, randomAttempts);

            // Phase 2: Prefer the safe coordinate that blocks the fewest
            // other currently safe coordinates.
            this.completeGreedily(remaining, queenSet, occupied);

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
     * Randomly samples distinct board coordinates.
     *
     * @param remaining coordinates not yet sampled
     * @param queens    currently placed queens
     * @param occupied  occupied attack lines
     * @param attempts  maximum number of random samples
     */
    private void placeRandomQueens(List<Coordinate> remaining,
                                   Set<Coordinate> queens,
                                   AttackLines occupied,
                                   int attempts) {
        for (int i = 0; i < attempts && !remaining.isEmpty(); i++) {
            int index = this.random.nextInt(remaining.size());
            Coordinate candidate = remaining.remove(index);
            this.tryAdd(candidate, queens, occupied);
        }
    }

    /**
     * Greedily selects safe cells that eliminate the fewest other
     * currently safe positions.
     *
     * @param remaining coordinates not sampled in the random phase
     * @param queens    currently placed queens
     * @param occupied  occupied attack lines
     */
    private void completeGreedily(List<Coordinate> remaining,
                                  Set<Coordinate> queens,
                                  AttackLines occupied) {
        while (true) {
            List<Coordinate> safeCells = new ArrayList<>();
            for (Coordinate cell : remaining) {
                if (occupied.isUnderAttack(cell)) {
                    safeCells.add(cell);
                }
            }
            if (safeCells.isEmpty()) {
                return;
            }

            Coordinate bestCell = null;
            int fewestBlocked = Integer.MAX_VALUE;
            for (Coordinate candidate : safeCells) {
                int blocked = this.countBlockedCells(candidate, safeCells);
                if (blocked < fewestBlocked
                        || (blocked == fewestBlocked
                        && this.comesBefore(candidate, bestCell))) {
                    bestCell = candidate;
                    fewestBlocked = blocked;
                }
            }

            this.tryAdd(bestCell, queens, occupied);
            remaining.remove(bestCell);
        }
    }

    /**
     * Counts the safe coordinates attacked by a candidate queen.
     *
     * @param candidate proposed queen coordinate
     * @param safeCells currently safe coordinates
     * @return number of other cells the candidate would attack
     */
    private int countBlockedCells(Coordinate candidate,
                                  List<Coordinate> safeCells) {
        int blocked = 0;
        for (Coordinate other : safeCells) {
            if (!candidate.equals(other)
                    && this.shareAttackLine(candidate, other)) {
                blocked++;
            }
        }
        return blocked;
    }

    /**
     * Checks whether two coordinates lie on the same queen attack line.
     *
     * @param first  first coordinate
     * @param second second coordinate
     * @return true if a queen at either position would attack the other
     */
    private boolean shareAttackLine(Coordinate first, Coordinate second) {
        int x1 = first.x();
        int y1 = first.y();
        int z1 = -(x1 + y1);
        int x2 = second.x();
        int y2 = second.y();
        int z2 = -(x2 + y2);

        return x1 == x2
                || y1 == y2
                || z1 == z2
                || x1 - y1 == x2 - y2
                || x1 - z1 == x2 - z2
                || y1 - z1 == y2 - z2;
    }

    /**
     * Breaks greedy ties using consistent coordinate ordering.
     *
     * @param candidate candidate position
     * @param best      currently preferred position, or null
     * @return true if candidate should win the tie
     */
    private boolean comesBefore(Coordinate candidate, Coordinate best) {
        return best == null
                || candidate.x() < best.x()
                || (candidate.x() == best.x()
                && candidate.y() < best.y());
    }

    /**
     * Places a queen only if the position is safe.
     *
     * @param cell     candidate coordinate
     * @param queens   set of placed queens
     * @param occupied occupied attack lines
     */
    private void tryAdd(Coordinate cell,
                        Set<Coordinate> queens,
                        AttackLines occupied) {
        if (occupied.isUnderAttack(cell) && queens.add(cell)) {
            occupied.record(cell);
        }
    }

    /**
     * Stores six hash-table-backed sets for constant-time attack checks.
     */
    private static final class AttackLines {
        private final Set<Integer> usedX = new HashSet<>();
        private final Set<Integer> usedY = new HashSet<>();
        private final Set<Integer> usedZ = new HashSet<>();
        private final Set<Integer> usedXY = new HashSet<>();
        private final Set<Integer> usedXZ = new HashSet<>();
        private final Set<Integer> usedYZ = new HashSet<>();

        /**
         * Checks whether a position is under attack.
         *
         * @param cell candidate coordinate
         * @return true if any queen already occupies its attack lines
         */
        boolean isUnderAttack(Coordinate cell) {
            int x = cell.x();
            int y = cell.y();
            int z = -(x + y);

            return !this.usedX.contains(x)
                    && !this.usedY.contains(y)
                    && !this.usedZ.contains(z)
                    && !this.usedXY.contains(x - y)
                    && !this.usedXZ.contains(x - z)
                    && !this.usedYZ.contains(y - z);
        }

        /**
         * Inserts all six attack-line keys for a queen.
         *
         * @param cell newly placed queen coordinate
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
