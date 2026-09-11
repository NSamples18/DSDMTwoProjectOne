package edu.westga.dsdm2project1;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point for the Hex N-Queens JavaFX application.
 */
public class Main extends Application {

    private static final String VIEW_PATH = "/edu/westga/dsdm2project1/Hexboard.fxml";

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource(VIEW_PATH));
        Scene scene = new Scene(loader.load());
        stage.setTitle("Hex N-Queens");
        stage.setScene(scene);
        stage.show();
    }

     static void main(String[] args) {
        launch(args);
    }
}