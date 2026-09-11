package edu.westga.dsdm2project1.View;

import edu.westga.dsdm2project1.Coordinate;
import edu.westga.dsdm2project1.ViewModel.HexBoardViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;

/**
 * View for the Hex N-Queens application.
 */
public class HexQueensView {

    private HexBoardViewModel viewModel = new HexBoardViewModel(3);
    @FXML
    private Pane boardPane;

    @FXML
    private TextField boardSizeField;

    @FXML
    private TextField cellSizeField;

    @FXML
    private Label messageLabel;

    @FXML
    private Button newBoardButton;

    @FXML
    private Label queenCountLabel;

    /**
     * Initializes the view and sets up the ViewModel .
     * Also is a handler for the new board button.
     *
     * @param actionEvent the action event
     */
    @FXML
    public void handleNewBoard(ActionEvent actionEvent) {
        try {
            int boardSize = Integer.parseInt(this.boardSizeField.getText());
            double cellSize = Double.parseDouble(this.cellSizeField.getText());

            if (boardSize < 1 || cellSize <= 0) {
                this.messageLabel.setText("Board size and cell size must be greater than 0.");
                return;
            }

            this.viewModel.createBoard(boardSize);

            int numberOfCells = this.viewModel.getBoardCoordinates().size();

            this.queenCountLabel.setText("Queens: 0");
            this.messageLabel.setText("Created board with " + numberOfCells + " cells.");
            this.renderBoard(cellSize, boardSize);
            this.boardPane.getChildren();

        } catch (NumberFormatException exception) {
            this.messageLabel.setText("Board size and cell size must be numbers.");
        }
    }

    private void renderBoard(double cellSize, int boardSize) {
        this.boardPane.getChildren().clear();

        for (Coordinate coordinate : this.viewModel.getBoardCoordinates()) {
            Polygon hexagon =
                    this.createHexagon(coordinate, cellSize, boardSize);

            this.boardPane.getChildren().add(hexagon);
        }
    }

    private Polygon createHexagon(
            Coordinate coordinate,
            double cellSize,
            int boardSize) {

        Polygon hexagon = new Polygon();

        double halfWidth = Math.sqrt(3) / 2 * cellSize;

        hexagon.getPoints().addAll(
                0.0, -cellSize,
                halfWidth, -cellSize / 2,
                halfWidth, cellSize / 2,
                0.0, cellSize,
                -halfWidth, cellSize / 2,
                -halfWidth, -cellSize / 2
        );

        int radius = boardSize - 1;

        double offsetX = 30 + Math.sqrt(3) * cellSize * radius;
        double offsetY = 30 + 1.5 * cellSize * radius;

        double centerX = offsetX
                + Math.sqrt(3) * cellSize
                * (coordinate.x() + coordinate.y() / 2.0);

        double centerY = offsetY
                + 1.5 * cellSize * coordinate.y();

        hexagon.setLayoutX(centerX);
        hexagon.setLayoutY(centerY);
        hexagon.setStyle(
                "-fx-fill: Orange;" +
                        "-fx-stroke: black;" +
                        "-fx-stroke-width: 1;"
        );

        return hexagon;
    }
}
