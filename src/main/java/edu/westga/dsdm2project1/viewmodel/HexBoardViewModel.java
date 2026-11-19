package edu.westga.dsdm2project1.viewmodel;

import edu.westga.dsdm2project1.Model.CliqueQueensSolver;
import edu.westga.dsdm2project1.Model.Coordinate;
import edu.westga.dsdm2project1.Model.HexBoard;

import edu.westga.dsdm2project1.Model.ProbabilisticQueenSolver;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * ViewModel for the HexBoard.
 */
public class HexBoardViewModel {
    private HexBoard board;
    private final StringProperty boardSize;
    private final StringProperty cellSize;
    private final StringProperty message;
    private final IntegerProperty queenCount;
    private final ObservableList<Coordinate> boardCoordinates;
    private final DoubleProperty currentCellSize;
    private final StringProperty repetitions;
    private final ProbabilisticQueenSolver solver;
    /**
     * Constants for the starter algorithm options.
     */
    public static final String NONE = "None";
    /**
     * Constants for the starter algorithm options.
     */
    public static final String PROBABILISTIC = "Probabilistic";
    /**
     * Constants for the starter algorithm options.
     */
    public static final String CLIQUE = "CLIQUE";
    private final StringProperty starterAlgorithm = new SimpleStringProperty(NONE);
    private final StringProperty timeLimitSeconds = new SimpleStringProperty("5");

    /**
     * Constructs a new HexBoardViewModel with default values.
     */
    public HexBoardViewModel() {
        this.boardSize = new SimpleStringProperty("3");
        this.cellSize = new SimpleStringProperty("30");
        this.currentCellSize = new SimpleDoubleProperty(30.0);
        this.message = new SimpleStringProperty("");
        this.repetitions = new SimpleStringProperty("100");
        this.solver = new ProbabilisticQueenSolver();

        this.queenCount = new SimpleIntegerProperty(0);

        this.boardCoordinates = FXCollections.observableArrayList();

        this.board = new HexBoard(3);

        this.refreshBoardState();

    }

    private void refreshBoardState() {
        this.boardCoordinates.setAll(this.board.getBoardCoordinates());

        this.queenCount.set(this.board.getNumberQueens());
    }

    /**
     * Creates a new HexBoard based on the current board size, cell size, and
     * repetitions properties, then places a starting set of non-attacking queens
     * found by the probabilistic solver.
     * If any input is invalid, an appropriate message is set.
     */
    public void createBoard() {
        int newBoardSize;
        double newCellSize;

        try {
            newBoardSize = Integer.parseInt(this.boardSize.get().trim());
            newCellSize = Double.parseDouble(this.cellSize.get().trim());
        } catch (NumberFormatException exception) {
            this.message.set("Board size and cell size must be numbers.");
            return;
        }

        if (newBoardSize < 1 || !Double.isFinite(newCellSize) || newCellSize <= 0) {
            this.message.set("Board size and cell size must be greater than 0.");
            return;
        }

        HexBoard newBoard = new HexBoard(newBoardSize);
        String resultMessage;

        switch (this.starterAlgorithm.get()) {
            case NONE -> {
                int cellCount = newBoard.getBoardCoordinates().size();
                resultMessage = "Created board with " + cellCount + " cells.";
            }

            case PROBABILISTIC -> {
                int attempts;
                try {
                    attempts = Integer.parseInt(this.repetitions.get().trim());
                } catch (NumberFormatException exception) {
                    this.message.set("Repetitions must be a positive whole number.");
                    return;
                }

                if (attempts < 1) {
                    this.message.set("Repetitions must be a positive whole number.");
                    return;
                }

                for (Coordinate queen : this.solver.solve(newBoardSize, attempts)) {
                    newBoard.setQueen(queen);
                }

                resultMessage = "Placed " + newBoard.getNumberQueens() + " queens.";
            }

            case CLIQUE -> {
                long seconds;
                try {
                    seconds = Long.parseLong(this.timeLimitSeconds.get().trim());
                } catch (NumberFormatException exception) {
                    this.message.set("Time limit must be a positive whole number of seconds.");
                    return;
                }

                if (seconds < 1) {
                    this.message.set("Time limit must be a positive whole number of seconds.");
                    return;
                }

                CliqueQueensSolver cliqueSolver = new CliqueQueensSolver(newBoardSize, seconds);
                for (Coordinate queen : cliqueSolver.solve()) {
                    newBoard.setQueen(queen);
                }

                resultMessage = "Placed " + newBoard.getNumberQueens() + " queens.";
                if (cliqueSolver.isTimeLimitReached()) {
                    resultMessage += " CLIQUE search reached the time limit.";
                }
            }

            default -> {
                this.message.set("Select a starter algorithm.");
                return;
            }
        }

        this.board = newBoard;
        this.currentCellSize.set(newCellSize);
        this.refreshBoardState();
        this.message.set(resultMessage);
    }

    /**
     * Returns the property for the starter algorithm.
     *
     * @return the property for the starter algorithm
     */
    public StringProperty starterAlgorithmProperty() {
        return this.starterAlgorithm;
    }

    /**
     * Returns the CLIQUE time limit entered by the user, in seconds.
     *
     * @return the property for the time limit
     */
    public StringProperty timeLimitSecondsProperty() {
        return this.timeLimitSeconds;
    }

    /**
     * Returns the property for the board size.
     *
     * @return the property for the board size
     */
    public StringProperty boardSizeProperty() {
        return this.boardSize;
    }

    /**
     * returns the cell size.
     *
     * @return the property for the cell size
     */
    public StringProperty cellSizeProperty() {
        return this.cellSize;
    }

    /**
     * Returns the property for the message.
     *
     * @return the property for the message
     */
    public StringProperty messageProperty() {
        return this.message;
    }

    /**
     * Returns the property for the queen count.
     *
     * @return the property for the queen count
     */
    public IntegerProperty queenCountProperty() {
        return this.queenCount;
    }

    /**
     * Returns the list of board coordinates.
     *
     * @return the list of board coordinates
     */
    public ObservableList<Coordinate> getBoardCoordinates() {
        return this.boardCoordinates;
    }

    /**
     * Returns the current cell size.
     *
     * @return the current cell size
     */
    public double getCurrentCellSize() {
        return this.currentCellSize.get();
    }

    /**
     * Removes the queen on the given cell if there is one, otherwise places a
     * queen there if it is legal to do so.
     *
     * @param coordinate the cell that was clicked
     */
    public void toggleQueen(Coordinate coordinate) {
        if (this.board.hasQueen(coordinate)) {
            this.board.unsetQueen(coordinate);
            this.queenCount.set(this.board.getNumberQueens());
            this.message.set("");
            return;
        }

        if (!this.board.setQueen(coordinate)) {
            this.message.set("That cell is under attack.");
            return;
        }

        this.queenCount.set(this.board.getNumberQueens());
        this.message.set("");
    }

    /**
     * Checks whether the given cell holds a queen.
     *
     * @param coordinate the cell to check
     * @return true if the cell holds a queen
     */
    public boolean hasQueen(Coordinate coordinate) {
        return this.board.hasQueen(coordinate);
    }

    /**
     * Checks whether the given cell is under attack by a queen.
     *
     * @param coordinate the cell to check
     * @return true if the cell is under attack
     */
    public boolean isUnderAttack(Coordinate coordinate) {
        return this.board.isUnderAttack(coordinate);
    }

    /**
     * Returns the property for the number of repetitions for the probabilistic
     * solver.
     *
     * @return the property for the number of repetitions
     */
    public Property<String> repetitionsProperty() {

        return this.repetitions;
    }
}