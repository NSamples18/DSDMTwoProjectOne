package edu.westga.dsdm2project1.View;

import edu.westga.dsdm2project1.Model.Coordinate;
import edu.westga.dsdm2project1.ViewModel.HexBoardViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

/**
 * View for the Hex N-Queens application.
 */
public class HexQueensView {

    private final HexBoardViewModel viewModel = new HexBoardViewModel();
    @FXML
    private Pane boardPane;

    @FXML
    private TextField boardSizeField;

    @FXML
    private TextField cellSizeField;

    @FXML
    private Label messageLabel;

    @FXML
    private Label queenCountLabel;

    /**
     * Initializes the view and sets up the ViewModel.
     */
    @FXML
    public void initialize() {
        this.boardSizeField.textProperty().bindBidirectional(this.viewModel.boardSizeProperty());
        this.cellSizeField.textProperty().bindBidirectional(this.viewModel.cellSizeProperty());
        this.messageLabel.textProperty().bindBidirectional(this.viewModel.messageProperty());
        this.queenCountLabel.textProperty().bind(this.viewModel.queenCountProperty().asString("Queens: %d"));
    }

    /**
     * Initializes the view and sets up the ViewModel .
     * Also is a handler for the new board button.
     *
     */
    @FXML
    public void handleNewBoard() {
        this.viewModel.createBoard();
        this.renderBoard();
    }

    /**
     * Renders the board.
     */
    private void renderBoard() {
        this.boardPane.getChildren().clear();

        double cellSize = this.viewModel.getCurrentCellSize();

        double halfWidth = Math.sqrt(3) / 2 * cellSize;

        double minX = Double.MAX_VALUE;
        double maxX = -Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = -Double.MAX_VALUE;

        /*
         * First pass:
         * Determine the total bounds of the board.
         */
        for (Coordinate coordinate : this.viewModel.getBoardCoordinates()) {

            double centerX = Math.sqrt(3) * cellSize * (coordinate.x() + coordinate.y() / 2.0);

            double centerY = 1.5 * cellSize * coordinate.y();

            minX = Math.min(minX, centerX - halfWidth);

            maxX = Math.max(maxX, centerX + halfWidth);

            minY = Math.min(minY, centerY - cellSize);

            maxY = Math.max(maxY, centerY + cellSize);
        }

        double boardWidth = maxX - minX;

        double boardHeight = maxY - minY;

        this.boardPane.setMinSize(boardWidth + 2 * cellSize, boardHeight + 2 * cellSize);

        double offsetX = Math.max((this.boardPane.getWidth() - boardWidth) / 2.0, cellSize) - minX;

        double offsetY = Math.max((this.boardPane.getHeight() - boardHeight) / 2.0, cellSize) - minY;


        for (Coordinate coordinate : this.viewModel.getBoardCoordinates()) {

            Polygon hexagon = this.createHexagon(coordinate, cellSize, offsetX, offsetY);

            this.boardPane.getChildren().add(hexagon);
        }
    }

    private void handleCellClicked(Coordinate coordinate) {
        this.viewModel.toggleQueen(coordinate);
        this.renderBoard();
    }

    private Polygon createHexagon(Coordinate coordinate, double cellSize, double offsetX, double offsetY) {

        Polygon hexagon = new Polygon();

        double halfWidth = Math.sqrt(3) / 2 * cellSize;

        hexagon.getPoints().addAll(0.0, -cellSize, halfWidth, -cellSize / 2, halfWidth, cellSize / 2, 0.0, cellSize, -halfWidth, cellSize / 2, -halfWidth, -cellSize / 2);

        double centerX = Math.sqrt(3) * cellSize * (coordinate.x() + coordinate.y() / 2.0);

        double centerY = 1.5 * cellSize * coordinate.y();

        hexagon.setLayoutX(centerX + offsetX);

        hexagon.setLayoutY(centerY + offsetY);

        if (this.viewModel.hasQueen(coordinate)) {
            hexagon.setStyle("-fx-fill: gold;" + "-fx-stroke: black;" + "-fx-stroke-width: 2;");
        } else if (this.viewModel.isUnderAttack(coordinate)) {
            hexagon.setStyle("-fx-fill: indianred;" + "-fx-stroke: black;" + "-fx-stroke-width: 1;");
        } else {
            hexagon.setStyle("-fx-fill: orange;" + "-fx-stroke: black;" + "-fx-stroke-width: 1;");
        }

        hexagon.setOnMouseClicked(_ -> this.handleCellClicked(coordinate));

        return hexagon;
    }
}
