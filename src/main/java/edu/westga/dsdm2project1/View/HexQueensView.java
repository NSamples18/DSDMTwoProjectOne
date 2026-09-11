package edu.westga.dsdm2project1.View;

import edu.westga.dsdm2project1.ViewModel.HexBoardViewModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

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
            this.boardPane.getChildren().clear();

        } catch (NumberFormatException exception) {
            this.messageLabel.setText("Board size and cell size must be numbers.");
        }
    }
}
