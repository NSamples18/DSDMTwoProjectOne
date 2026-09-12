package edu.westga.dsdm2project1.ViewModel;

import edu.westga.dsdm2project1.Coordinate;
import edu.westga.dsdm2project1.Model.HexBoard;

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
/**
     * Constructs a new HexBoardViewModel with default values.
     */
    public HexBoardViewModel() {
        this.boardSize = new SimpleStringProperty("3");
        this.cellSize = new SimpleStringProperty("30");
        this.currentCellSize = new SimpleDoubleProperty(30.0);
        this.message = new SimpleStringProperty("");

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
     * Creates a new HexBoard based on the current board size and cell size properties.
     * If the board size or cell size is invalid, an appropriate message is set.
     */
    public void createBoard() {
        try {
            int newBoardSize = Integer.parseInt(this.boardSize.get());

            double newCellSize = Double.parseDouble(this.cellSize.get());

            if (newBoardSize < 1 || newCellSize <= 0) {
                this.message.set("Board size and cell size must be greater than 0.");
                return;
            }

            this.board = new HexBoard(newBoardSize);

            this.currentCellSize.set(newCellSize);
            this.boardCoordinates.setAll(this.board.getBoardCoordinates());
            this.queenCount.set(this.board.getNumberQueens());

            int numberOfCells = this.board.getBoardCoordinates().size();

            this.message.set("Created board with " + numberOfCells + " cells.");

        } catch (NumberFormatException exception) {
            this.message.set("Board size and cell size must be numbers.");
        }
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
     * @return true if the board changed, false otherwise
     */
    public boolean toggleQueen(Coordinate coordinate) {
        if (this.board.hasQueen(coordinate)) {
            this.board.unsetQueen(coordinate);
            this.queenCount.set(this.board.getNumberQueens());
            this.message.set("");
            return true;
        }

        if (!this.board.setQueen(coordinate)) {
            this.message.set("That cell is under attack.");
            return false;
        }

        this.queenCount.set(this.board.getNumberQueens());
        this.message.set("");
        return true;
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
}