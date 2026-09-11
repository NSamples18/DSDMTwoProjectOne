package edu.westga.dsdm2project1.View;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class HexQueensView {



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

    @FXML
    void handleNewBoard(ActionEvent event) {

    }
}
